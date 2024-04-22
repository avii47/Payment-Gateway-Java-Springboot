package com.example.Payment.service;

import com.example.Payment.dto.PaymentDto;
import com.example.Payment.dto.StripeChargeDto;
import com.example.Payment.dto.StripeTokenDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class StripeService {

    PaymentDto paymentDto = new PaymentDto();

    @Autowired
    private PaymentService paymentService;


    @Value("${spring.sendgrid.api-key}")
    private String SecretStripeApiKey;

    @Value("${api.stripe.key}")
    private String PublishedStripeApiKey;

    @PostConstruct
    private void init(){

    }

    public StripeTokenDto createCardToken(StripeTokenDto model){

        Stripe.apiKey = PublishedStripeApiKey;

        try{

            Map<String, Object> card = new HashMap<>();
            card.put("number", model.getCardNumber());
            card.put("exp_month", Integer.parseInt(model.getExpMonth()));
            card.put("exp_year", Integer.parseInt(model.getExpYear()));
            card.put("cvc", model.getCvc());
            Map<String, Object> params = new HashMap<>();
            params.put("card", card);
            Token token = Token.create(params);
            if (token != null && token.getId() != null){
                model.setSuccess(true);
                model.setToken(token.getId());

                paymentDto.setCardNumber(card.get("number").toString());
                paymentDto.setExpMonth(card.get("exp_month").toString());
                paymentDto.setExpYear(card.get("exp_year").toString());
                paymentDto.setCvc(card.get("cvc").toString());
                paymentDto.setUsername(model.getUsername());
            }
            return model;
        }
        catch (StripeException e){
            log.error("StripeService (createCardToken)", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public StripeChargeDto charge(StripeChargeDto chargeRequest) {

        Stripe.apiKey = SecretStripeApiKey;

        try{

            chargeRequest.setSuccess(false);

            Map<String, Object> chargeParms = new HashMap<>();
            chargeParms.put("amount", (int) (chargeRequest.getAmount() * 100));
            chargeParms.put("currency", "USD");
            chargeParms.put("description", "Payment for id " + chargeRequest.getAdditionalInfo().getOrDefault("ID_TAG", ""));
            chargeParms.put("source", chargeRequest.getStripeToken());
            Map<String, Object> metaData = new HashMap<>();
            metaData.put("id", chargeRequest.getChargeId());
            metaData.putAll(chargeRequest.getAdditionalInfo());
            chargeParms.put("metadata", metaData);
            Charge charge = Charge.create(chargeParms);
            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

            if(charge.getPaid()) {
                chargeRequest.setChargeId(charge.getId());
                chargeRequest.setSuccess(true);

                paymentDto.setAmount(chargeRequest.getAmount());
                paymentDto.setSuccess(chargeRequest.getMessage());
                paymentService.savePayment(paymentDto);
            }
            return chargeRequest;
        }
        catch (StripeException e) {
            log.error("StripeService (charge)", e);
            throw  new RuntimeException(e.getMessage());
        }
    }
}
