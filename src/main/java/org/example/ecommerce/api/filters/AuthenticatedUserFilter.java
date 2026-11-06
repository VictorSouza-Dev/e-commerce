package org.example.ecommerce.api.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ecommerce.domain.repositories.user.UserRepository;
import org.example.ecommerce.domain.security.tokens.AccessTokenValidator;
import org.example.ecommerce.domain.security.tokens.TokenProvider;
import org.example.ecommerce.shared.communication.response.ResponseErrorJson;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class AuthenticatedUserFilter extends OncePerRequestFilter {

    private final AccessTokenValidator validator;
    private final UserRepository repository;
    private final TokenProvider tokenProvider;
    private final ObjectMapper mapper;

    public AuthenticatedUserFilter(
            AccessTokenValidator validator,
            UserRepository repository,
            TokenProvider tokenProvider,
            ObjectMapper mapper
    ) {
        this.validator = validator;
        this.repository = repository;
        this.tokenProvider = tokenProvider;
        this.mapper = mapper;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        String method = request.getMethod();

        return method.equalsIgnoreCase("OPTIONS") || path.startsWith("/auth");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        var token = tokenProvider.value();

        if (token == null || token.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            var userIdentifier = validator.validateAndGetUserIdentifier(token);
            var exist = repository.existsActiveUserByIdentifier(userIdentifier);

            if (!exist) {
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

        } catch (Exception e) {
            writeResponseErrorMessage(response);
        }
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
}