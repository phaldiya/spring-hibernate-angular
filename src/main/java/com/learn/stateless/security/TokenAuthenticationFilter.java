package com.learn.stateless.security;

import com.learn.service.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends StatelessLoginFilter {

    public TokenAuthenticationFilter(String urlMapping,
                                     TokenAuthenticationService tokenAuthenticationService,
                                     UserDetailsService userDetailsService,
                                     AuthenticationManager authManager) {
        super(urlMapping, tokenAuthenticationService, userDetailsService, authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return super.getTokenAuthenticationService().getAuthentication(request);
    }
}
