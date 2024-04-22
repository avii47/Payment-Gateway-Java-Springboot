package com.example.Payment.dto;

import lombok.Data;

@Data
public class PaymentDto {

    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvc;
    private String username;
    private  String userId = "003";
    private  String projectId = "004";
    private  Double amount;
    private  String success;

}


