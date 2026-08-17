package org.example.ecommerce.api.token;

import org.example.ecommerce.domain.security.tokens.TokenProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class HttpContextTokenValue implements TokenProvider {

    @Override
    public String value(){
        var attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

        var authorization = attributes.getRequest().getHeader("Authorization");

        return authorization.contains("Bearer") ? authorization.substring(7).trim() : authorization;
    }
}
