package com.splat_spb.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import static com.splat_spb.dto.error.ErrorCode.AUTHENTICATION_FAILURE;

/**
 * Custom OAuth2 runtime exception.
 *
 * @author Niyaz_Bikbaev
 **/
public class ApplicationOAuth2Exception extends OAuth2Exception {
    public ApplicationOAuth2Exception(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return AUTHENTICATION_FAILURE.toString();
    }
}
