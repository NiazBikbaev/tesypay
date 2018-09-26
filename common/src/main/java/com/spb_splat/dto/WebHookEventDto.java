package com.spb_splat.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Default comment.
 **/
public class WebHookEventDto implements Serializable {
    private String id;
    private String currency;
    private String amount;
    private String externalId;
    private State status;
    private String signature;

    /**
     * Getter for id.
     *
     * @return java.lang.String
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for id.
     *
     * @param id value
     */
    public void setId(String id) {
        this.id = id;
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
     * Getter for amount.
     *
     * @return java.lang.String
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Setter for amount.
     *
     * @param amount value
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

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
     * Getter for status.
     *
     * @return com.test.testpay.dto.payment.State
     */
    public State getStatus() {
        return status;
    }

    /**
     * Setter for status.
     *
     * @param status value
     */
    public void setStatus(State status) {
        this.status = status;
    }

    /**
     * Getter for signature.
     *
     * @return java.lang.String
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Setter for signature.
     *
     * @param signature value
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebHookEventDto)) return false;
        WebHookEventDto that = (WebHookEventDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(currency, that.currency)
                && Objects.equals(amount, that.amount)
                && Objects.equals(externalId, that.externalId)
                && status == that.status
                && Objects.equals(signature, that.signature);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, currency, amount, externalId, status, signature);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "WebHookEventDto{"
                + "id='" + id + '\''
                + ", currency='" + currency + '\''
                + ", amount='" + amount + '\''
                + ", externalId='" + externalId + '\''
                + ", status=" + status
                + ", signature='" + signature + '\''
                + '}';
    }
}
