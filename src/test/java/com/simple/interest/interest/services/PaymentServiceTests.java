package com.simple.interest.interest.services;

import com.simple.interest.controllers.requests.CalculatePaymentRequest;
import com.simple.interest.controllers.responses.CalculatePaymentResponse;
import com.simple.interest.repositories.CreditRepository;
import com.simple.interest.services.PaymentServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

@SpringBootTest
public class PaymentServiceTests {

    @InjectMocks
    private PaymentServiceImpl service;

    @Mock
    private CreditRepository creditRepository;

    @Test
    public void when_size() {

        CalculatePaymentRequest request = new CalculatePaymentRequest();
        request.setAmount(100D);
        request.setRate(.20D);
        request.setTerms(4);

        List<CalculatePaymentResponse.Payment> paymentList = service.calculatePayment(request);

        Assertions.assertThat(paymentList.size()).isEqualTo(request.getTerms());

    }

    @Test
    public void when_percentage() {

        CalculatePaymentRequest request = new CalculatePaymentRequest();
        request.setAmount(100D);
        request.setRate(.20D);
        request.setTerms(4);

        List<CalculatePaymentResponse.Payment> paymentList = service.calculatePayment(request);

        BigDecimal a = BigDecimal.valueOf(request.getAmount() * (1 + request.getRate()));
        BigDecimal roundOff = a.setScale(2, RoundingMode.UP);

        Assertions.assertThat(paymentList.stream().map(CalculatePaymentResponse.Payment::getAmount).mapToDouble(Double::doubleValue).sum()).isEqualTo(roundOff.doubleValue());

    }

    @Test
    public void when_lastWeek() {

        CalculatePaymentRequest request = new CalculatePaymentRequest();
        request.setAmount(100D);
        request.setRate(.20D);
        request.setTerms(4);

        List<CalculatePaymentResponse.Payment> paymentList = service.calculatePayment(request);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, request.getTerms());
        Assertions.assertThat(paymentList.get(paymentList.size() - 1).getPaymentDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()).isEqualTo(calendar.getTime().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
    }

}
