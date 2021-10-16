package com.simple.interest.controllers;

import com.simple.interest.controllers.requests.CalculatePaymentRequest;
import com.simple.interest.controllers.responses.CalculatePaymentResponse;
import com.simple.interest.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "calculate-payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CalculatePaymentResponse.Payment> calculatePayment(@RequestBody @Valid CalculatePaymentRequest request) {

        return paymentService.calculatePayment(request);

    }


}
