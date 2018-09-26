package com.splat_spb.dto.payment;

import com.splat_spb.validation.annotation.CurrencyIso4217;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Amount DTO.
 *
 * @author Niyaz_Bikbaev
 **/
public class AmountDto {
    @Digits(integer = 8, fraction = 2)
    private String value;
    @NotNull
    @CurrencyIso4217(message = "currency not compliant with the ISO-4217")
    private String currency;

    /**
     * Getter for value.
     *
     * @return double
     */
    public @Digits(integer = 8, fraction = 2) String getValue() {
        return value;
    }

    /**
     * Setter for value.
     *
     * @param value value
     */
    public void setValue(@Digits(integer = 8, fraction = 2) String value) {
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
        if (!(o instanceof AmountDto)) return false;
        AmountDto dto = (AmountDto) o;
        return Objects.equals(value, dto.value)
                && Objects.equals(currency, dto.currency);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }
}
