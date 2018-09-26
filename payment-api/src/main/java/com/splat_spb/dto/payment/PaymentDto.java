package com.splat_spb.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spb_splat.dto.State;
import com.splat_spb.validation.annotation.EnumValue;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/**
 * Payment DTO.
 *
 * @author Niyaz_Bikbaev
 **/
public class PaymentDto {
    @JsonProperty(access = WRITE_ONLY)
    private String id;

    @NotNull(message = "intent should not be null")
    @EnumValue(value = Intent.class, message = "Not valid intent value")
    private Intent intent;

    @NotNull(message = "notification_url should not be null")
    @NotEmpty(message = "notification_url should not be empty")
//    @Pattern(regexp = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+" +
//            "([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$", message = "notification_url not valid URL")
    private String notificationUrl;

    @Valid
    @NotNull(message = "payer should not be null")
    private PayerDto payer;

    @Valid
    @NotNull(message = "transaction should not be null")
    private TransactionDto transaction;

    @JsonProperty(access = WRITE_ONLY)
    private State state;

    @JsonProperty(access = WRITE_ONLY)
    private Instant createTime;

    /**
     * Getter for intent.
     *
     * @return com.test.testpay.dto.Intent
     */
    public Intent getIntent() {
        return intent;
    }

    /**
     * Setter for intent.
     *
     * @param intent value
     */
    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    /**
     * Getter for notificationUrl.
     *
     * @return java.lang.String
     */
    public String getNotificationUrl() {
        return notificationUrl;
    }

    /**
     * Setter for notificationUrl.
     *
     * @param notificationUrl value
     */
    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    /**
     * Getter for payer.
     *
     * @return com.test.testpay.dto.PayerDto
     */
    public PayerDto getPayer() {
        return payer;
    }

    /**
     * Setter for payer.
     *
     * @param payer value
     */
    public void setPayer(PayerDto payer) {
        this.payer = payer;
    }

    /**
     * Getter for transaction.
     *
     * @return com.test.testpay.dto.TransactionDto
     */
    public TransactionDto getTransaction() {
        return transaction;
    }

    /**
     * Setter for transaction.
     *
     * @param transaction value
     */
    public void setTransaction(TransactionDto transaction) {
        this.transaction = transaction;
    }

    /**
     * Getter for state.
     *
     * @return com.test.testpay.dto.response.State
     */
    public State getState() {
        return state;
    }

    /**
     * Setter for state.
     *
     * @param state value
     */
    public void setState(State state) {
        this.state = state;
    }

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
     * Getter for createTime.
     *
     * @return java.time.Instant
     */
    public Instant getCreateTime() {
        return createTime;
    }

    /**
     * Setter for createTime.
     *
     * @param createTime value
     */
    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentDto)) return false;
        PaymentDto that = (PaymentDto) o;
        return Objects.equals(id, that.id)
                && intent == that.intent
                && Objects.equals(notificationUrl, that.notificationUrl)
                && Objects.equals(payer, that.payer)
                && Objects.equals(transaction, that.transaction)
                && state == that.state
                && Objects.equals(createTime, that.createTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, intent, notificationUrl, payer, transaction, state, createTime);
    }
}
