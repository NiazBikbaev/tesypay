package com.splat_spb.entity;

import com.spb_splat.dto.State;
import com.splat_spb.dto.payment.Intent;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Objects;

/**
 * Default comment.
 **/
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Payment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private Payer payer;
    @Enumerated(value = EnumType.STRING)
    private Intent intent;
    private String notificationUrl;
    @Enumerated(value = EnumType.STRING)
    private State state;
    @CreatedDate
    private Instant createTime;
    private Transaction transaction;

    /**
     * Getter for id.
     *
     * @return java.util.UUID
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
     * Getter for payer.
     *
     * @return com.test.testpay.entity.Payer
     */
    public Payer getPayer() {
        return payer;
    }

    /**
     * Setter for payer.
     *
     * @param payer value
     */
    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    /**
     * Getter for intent.
     *
     * @return com.test.testpay.dto.payment.Intent
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
     * Getter for transaction.
     *
     * @return com.test.testpay.entity.Transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * Setter for transaction.
     *
     * @param transaction value
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id)
                && Objects.equals(payer, payment.payer)
                && intent == payment.intent
                && Objects.equals(notificationUrl, payment.notificationUrl)
                && state == payment.state
                && Objects.equals(transaction, payment.transaction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, payer, intent, notificationUrl, state, transaction);
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
}
