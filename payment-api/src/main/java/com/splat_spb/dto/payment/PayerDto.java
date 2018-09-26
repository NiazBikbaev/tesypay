package com.splat_spb.dto.payment;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Payer DTO.
 *
 * @author Niyaz_Bikbaev
 **/
public class PayerDto {
    @Email
    @NotNull(message = "email should not be null")
    private String email;

    /**
     * Getter for email.
     *
     * @return java.lang.String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email.
     *
     * @param email value
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayerDto)) return false;
        PayerDto payerDto = (PayerDto) o;
        return Objects.equals(email, payerDto.email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
