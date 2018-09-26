package com.splat_spb.config.oauth2;

import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;

import javax.servlet.http.HttpServletRequest;

/**
 * Extract access token from Authorization header.
 *
 * @author Niyaz_Bikbaev
 **/
public class AuthorizationHeaderBearerTokenExtractor extends BearerTokenExtractor {
    @Override
    protected String extractToken(HttpServletRequest request) {
        return extractHeaderToken(request);
    }
}
