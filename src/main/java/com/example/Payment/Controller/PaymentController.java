package com.example.Payment.Controller;

import com.example.Payment.entity.Payment;
import com.example.Payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(path="/payments")
    public List<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @GetMapping(path = "/find/current/amounts", params= "Project_Id")
    public BigDecimal findPaymentByProjectId(@RequestParam String Project_Id){
        return paymentService.findPaymentByProjectId(Project_Id);
    }

    /*@PostMapping(path="/payments")
    public void createPayment(){
        paymentService.savePayment(new PaymentDto());
    }

    @PutMapping(path = "/payments")
    public Payment updatePayment(@RequestBody Payment payment) {
        return paymentService.updatePayment(payment);
    }

    @DeleteMapping(path = "/payments/{id}")
    public void deletePaymentById(@PathVariable int id) {
        //paymentService.deletePaymentById(id);
    }*/
}
