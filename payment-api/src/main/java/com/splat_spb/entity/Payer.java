package com.splat_spb.entity;

import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Default comment.
 **/
@Embeddable
public class Payer {
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
        if (!(o instanceof Payer)) return false;
        Payer payer = (Payer) o;
        return Objects.equals(email, payer.email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
