package com.splat_spb.rest;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.util.Set;

/**
 * Default comment.
 **/
public class OAuth2RequestPostProcessor implements RequestPostProcessor {

    private final AuthorizationServerTokenServices tokenService;
    private final String clientId;
    private final Set<String> scopes;

    private OAuth2RequestPostProcessor(AuthorizationServerTokenServices tokenService,
                                       String clientId, Set<String> scopes) {
        this.tokenService = tokenService;
        this.clientId = clientId;
        this.scopes = scopes;
    }

    @Override
    public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
        OAuth2AccessToken token = createAccessToken();
        request.addHeader("Authorization", "Bearer " + token.getValue());
        return request;
    }

    private OAuth2AccessToken createAccessToken() {
        OAuth2Request oAuth2Request = new OAuth2Request(null, clientId, null,
                true, scopes, null, null, null, null);
        OAuth2Authentication auth = new OAuth2Authentication(oAuth2Request, null);
        return tokenService.createAccessToken(auth);
    }

    public static RequestPostProcessor withClient(AuthorizationServerTokenServices tokenService,
                                                  String clientId, Set<String> scopes) {
        return new OAuth2RequestPostProcessor(tokenService, clientId, scopes);
    }

}
