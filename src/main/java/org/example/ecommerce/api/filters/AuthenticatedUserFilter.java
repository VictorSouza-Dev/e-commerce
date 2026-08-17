package org.example.ecommerce.api.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ecommerce.domain.repositories.user.UserRepository;
import org.example.ecommerce.domain.security.tokens.AccessTokenValidator;
import org.example.ecommerce.shared.communication.response.ResponseErrorJson;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class AuthenticatedUserFilter extends OncePerRequestFilter {

    private final AccessTokenValidator validator;
    private final UserRepository repository;
    private final ObjectMapper mapper;

    public AuthenticatedUserFilter(
            AccessTokenValidator validator,
            UserRepository repository,
            ObjectMapper mapper
    ){
        this.validator = validator;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        var token = tokenOnRequest(request);

        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }

        try{
            var userIdentifier = validator.validateAndGetUserIdentifier(token);

            var exist = repository.existsActiveUserByIdentifier(userIdentifier);

            if(!exist){
                writeResponseErrorMessage(response);
                return;
            }

            var authentication = new UsernamePasswordAuthenticationToken(
                    userIdentifier,
                    null,
                    Collections.emptyList()
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);

        }catch (Exception e){
            writeResponseErrorMessage(response);
            return;
        }
    }

    private String tokenOnRequest(HttpServletRequest request) {
        var token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7);
    }

    private void writeResponseErrorMessage(
            HttpServletResponse response
    ) throws IOException {

        var json = mapper.writeValueAsString(new ResponseErrorJson(List.of("Token Invalid")));

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/auth");
    }
}
