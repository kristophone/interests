package com.simple.interest.services;

import com.simple.interest.controllers.requests.CalculatePaymentRequest;
import com.simple.interest.controllers.responses.CalculatePaymentResponse;

import java.util.List;

public interface PaymentService {

    List<CalculatePaymentResponse.Payment> calculatePayment(CalculatePaymentRequest request);

}
