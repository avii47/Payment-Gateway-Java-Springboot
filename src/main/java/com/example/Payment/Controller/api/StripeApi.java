package com.example.Payment.Controller.api;

import com.example.Payment.dto.StripeChargeDto;
import com.example.Payment.dto.StripeTokenDto;
import com.example.Payment.service.StripeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stripe")
@AllArgsConstructor
public class StripeApi {

    private final StripeService stripeService;

    @PostMapping("/card/tokens")
    @ResponseBody
    public StripeTokenDto createCardToken(@RequestBody StripeTokenDto model) {

        return stripeService.createCardToken(model);
    }

    @PostMapping("/charges")
    @ResponseBody
    public StripeChargeDto charge(@RequestBody StripeChargeDto model) {

        return stripeService.charge(model);
    }
}
