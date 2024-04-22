package com.example.Payment.entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="payment_recodes_tbl")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String paymentId;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "exp_month")
    private String expMonth;
    @Column(name = "exp_year")
    private String expYear;
    @Column(name = "cvc")
    private String cvc;
    @Column(name = "user_name")
    private String username;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "project_id")
    private String projectId;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "success")
    private String success;


    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}

