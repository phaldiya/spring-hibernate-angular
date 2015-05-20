package com.learn.stateless.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.domain.User;
import com.learn.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;

@Service
public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;

    private final TokenHandler tokenHandler;

    @Autowired
    private UserRepository userRepo;

    @Value("${spring.profiles.active}")
    protected String activeProfile;

    @Autowired
    public TokenAuthenticationService(@Value("${token.secret}") String secret) {
        tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(secret));
    }

    public void addAuthentication(HttpServletResponse response,
                                  UserAuthentication authentication) {
        final User user = authentication.getDetails();
        user.setExpires(System.currentTimeMillis() + TEN_DAYS);
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
    }

    public Authentication getAuthentication(HttpServletRequest request) throws IOException {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final User user = tokenHandler.parseUserFromToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        } else {
            final Spoof spoof = getSpoof(request.getInputStream());
            if (spoof != null) {
                final User user = userRepo.findByUsername(spoof.getSpoof());
                if (user != null) {
                    return new UserAuthentication(user);
                }
            }
        }

        return null;
    }

    private Spoof getSpoof(InputStream stream) {
        try {
            // skip spoofing on production
            if (StringUtils.isNotBlank(activeProfile) && !activeProfile.equalsIgnoreCase("prod")) {
                return new ObjectMapper().readValue(stream, Spoof.class);
            }
        } catch (Exception e) {
            // do nothing
        }

        return null;
    }
}