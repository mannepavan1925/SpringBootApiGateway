package com.pavan.ApiGateWay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavan.ApiGateWay.beans.User;
import com.pavan.ApiGateWay.beans.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class TokenAuthenticationService {
    @Autowired
    private TokenService tokenService;

    private static final String AUTH_HEADER_NAME = "access-token";
    private static final long DAY = 1000 * 60 * 60 * 24;

    private ObjectMapper mapper = new ObjectMapper();

    public Authentication getAuthentication(HttpServletRequest request) throws IOException {
        final String tokenString = request.getHeader(AUTH_HEADER_NAME);

        if (tokenString != null) {
            Token token = tokenService.verifyToken(tokenString);
            final User user = mapper.readValue(token.getExtendedInformation(), User.class);

            if (user != null && (System.currentTimeMillis() - token.getKeyCreationTime()) < DAY) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}
