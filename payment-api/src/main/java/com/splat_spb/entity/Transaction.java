package com.splat_spb.entity;

import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Default comment.
 **/
@Embeddable
public class Transaction {
    private Amount amount;
    private String externalId;
    private String description;

    /**
     * Getter for externalId.
     *
     * @return java.lang.String
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * Setter for externalId.
     *
     * @param externalId value
     */
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    /**
     * Getter for amount.
     *
     * @return com.test.testpay.entity.Amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Setter for amount.
     *
     * @param amount value
     */
    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    /**
     * Getter for description.
     *
     * @return java.lang.String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description.
     *
     * @param description value
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(amount, that.amount)
                && Objects.equals(externalId, that.externalId)
                && Objects.equals(description, that.description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(amount, externalId, description);
    }
}
