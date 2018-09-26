package com.splat_spb.rest;

import com.splat_spb.dto.error.ErrorDto;
import com.splat_spb.exception.ApplicationApiException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import javax.persistence.PersistenceException;

import static com.splat_spb.dto.error.ErrorCode.AUTHENTICATION_FAILURE;
import static com.splat_spb.dto.error.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.splat_spb.dto.error.ErrorCode.INVALID_REQUEST;
import static com.splat_spb.dto.error.ErrorCode.UNSUPPORTED_MEDIA_TYPE;

/**
 * Global exception handler.
 *
 * @author Niyaz_Bikbaev
 **/
@RestController
@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationApiException.class)
    public ResponseEntity<ErrorDto> apiExceptionHandler(ApplicationApiException apiError) {
        ErrorDto errorDto =
                new ErrorDto(apiError.getErrorCode(), apiError.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({OAuth2Exception.class, AccessDeniedException.class, AuthenticationException.class})
    public ResponseEntity<ErrorDto> oauth2ExceptionHandler(Exception e) {
        ErrorDto errorDto =
                new ErrorDto(AUTHENTICATION_FAILURE, e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({PersistenceException.class, DataAccessException.class})
    public ResponseEntity<ErrorDto> persistenceExceptionHandler(Exception e) {
        ErrorDto errorDto =
                new ErrorDto(INTERNAL_SERVER_ERROR, e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDto errorDto =
                new ErrorDto(UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDto errorDto =
                new ErrorDto(INVALID_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        ErrorDto errorDto =
                new ErrorDto(INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
