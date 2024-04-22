/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Payment.service;

import com.example.Payment.dto.PaymentDto;
import com.example.Payment.entity.Payment;
import com.example.Payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author User
 */
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment>getAllPayments(){
        return paymentRepository.findAll();
    }

    public BigDecimal findPaymentByProjectId(String PrId){

        return paymentRepository.getTotalAmountByProjectId(PrId);
    }
    
    public void savePayment(PaymentDto model){
        Payment payment = new Payment();
        payment.setCardNumber(model.getCardNumber());
        payment.setExpMonth(model.getExpMonth());
        payment.setExpYear(model.getExpYear());
        payment.setCvc(model.getCvc());
        payment.setUsername(model.getUsername());
        payment.setUserId(model.getUserId());
        payment.setProjectId(model.getProjectId());
        payment.setAmount(model.getAmount());
        payment.setSuccess(model.getSuccess());

        paymentRepository.save(payment);

    }
    
    public Payment updatePayment(Payment payment){
        return paymentRepository.save(payment);
    }
}
