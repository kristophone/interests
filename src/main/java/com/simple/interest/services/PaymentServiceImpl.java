package com.simple.interest.services;

import com.simple.interest.controllers.requests.CalculatePaymentRequest;
import com.simple.interest.controllers.responses.CalculatePaymentResponse;
import com.simple.interest.models.Credit;
import com.simple.interest.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private CreditRepository creditRepository;

    @Override
    @Transactional
    public List<CalculatePaymentResponse.Payment> calculatePayment(CalculatePaymentRequest request) {

        /*
         * I = C * i * t
         *
         * I = Interés
         * C = Capital inicial
         * i = Tasa de interés
         * t = Tiempo
         *
         */

        double I = request.getAmount() * (request.getRate() + 1);

        double paymentPerWeek = I / request.getTerms();

        BigDecimal a = new BigDecimal(paymentPerWeek);
        BigDecimal roundOff = a.setScale(2, RoundingMode.UP);

        CalculatePaymentResponse response = new CalculatePaymentResponse();
        response.setPaymentList(new ArrayList<>());

        for (int term = 0; term < request.getTerms(); term++) {

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.WEEK_OF_YEAR, term + 1);
            response.getPaymentList().add(new CalculatePaymentResponse.Payment(
                    term,
                    roundOff.doubleValue(),
                    calendar.getTime()
            ));

        }

        Credit credit = new Credit();
        credit.setAmount(request.getAmount());
        credit.setRate(request.getRate());
        credit.setTerms(request.getTerms());
        credit.setPaymentList(response.getPaymentList().stream().map(payment -> {
            Credit.Payment p = new Credit.Payment();
            p.setAmount(payment.getAmount());
            p.setPaymentNumber(payment.getPaymentNumber());
            p.setPaymentDate(Instant.ofEpochMilli(payment.getPaymentDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            p.setCreatedAt(LocalDateTime.now());
            return p;
        }).collect(Collectors.toList()));
        credit.setCreatedAt(LocalDateTime.now());

        creditRepository.save(credit);

        return response.getPaymentList();
    }

}
