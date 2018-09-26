package com.splat_spb.dto.payment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Transaction DTO.
 *
 * @author Niyaz_Bikbaev
 **/
public class TransactionDto {
    private String externalId;
    @NotNull
    @Valid
    private AmountDto amount;
    private String description;

    /**
     * Getter for externalId.
     *
     * @return long
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
     * @return com.test.testpay.dto.AmountDto
     */
    public AmountDto getAmount() {
        return amount;
    }

    /**
     * Setter for amount.
     *
     * @param amount value
     */
    public void setAmount(AmountDto amount) {
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
        if (!(o instanceof TransactionDto)) return false;
        TransactionDto dto = (TransactionDto) o;
        return Objects.equals(externalId, dto.externalId)
                && Objects.equals(amount, dto.amount)
                && Objects.equals(description, dto.description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(externalId, amount, description);
    }
}
