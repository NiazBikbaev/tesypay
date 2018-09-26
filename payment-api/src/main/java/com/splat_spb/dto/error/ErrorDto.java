package com.splat_spb.dto.error;

import java.util.Objects;

/**
 * Error DTO.
 *
 * @author Niyaz_Bikbaev
 **/
public class ErrorDto {
    private ErrorCode error;
    private String errorDescription;

    public ErrorDto(ErrorCode error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    /**
     * Getter for error.
     *
     * @return java.lang.String
     */
    public ErrorCode getError() {
        return error;
    }

    /**
     * Getter for errorDescription.
     *
     * @return java.lang.String
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorDto)) return false;
        ErrorDto errorDto = (ErrorDto) o;
        return Objects.equals(error, errorDto.error)
                && Objects.equals(errorDescription, errorDto.errorDescription);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(error, errorDescription);
    }
}
