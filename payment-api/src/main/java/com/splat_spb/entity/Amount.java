package com.splat_spb.entity;

import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Default comment.
 **/
@Embeddable
public class Amount {
    private String value;
    private String currency;

    /**
     * Getter for value.
     *
     * @return double
     */
    public @javax.validation.constraints.Digits(integer = 8, fraction = 2) String getValue() {
        return value;
    }

    /**
     * Setter for value.
     *
     * @param value value
     */
    public void setValue(@javax.validation.constraints.Digits(integer = 8, fraction = 2) String value) {
        this.value = value;
    }

    /**
     * Getter for currency.
     *
     * @return java.lang.String
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Setter for currency.
     *
     * @param currency value
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amount)) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value)
                && Objects.equals(currency, amount.currency);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }
}
