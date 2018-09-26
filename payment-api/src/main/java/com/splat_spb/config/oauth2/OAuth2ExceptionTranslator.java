package com.splat_spb.config.oauth2;

import com.splat_spb.exception.ApplicationOAuth2Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * Custom {@link WebResponseExceptionTranslator} implementation.
 *
 * @author Niyaz_Bikbaev
 **/
@Component
public class OAuth2ExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(new ApplicationOAuth2Exception(e.getMessage()));
    }
}
