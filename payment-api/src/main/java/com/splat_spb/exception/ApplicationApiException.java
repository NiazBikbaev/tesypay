package com.splat_spb.exception;


import com.splat_spb.dto.error.ErrorCode;

/**
 * Global exception.
 *
 * @author Niyaz_Bikbaev
 **/
public class ApplicationApiException extends RuntimeException {
    private final ErrorCode errorCode;

    public ApplicationApiException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Getter for errorCode.
     *
     * @return com.test.testpay.dto.ErrorCode
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
