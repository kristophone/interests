package com.simple.interest.interest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.interest.controllers.PaymentController;
import com.simple.interest.controllers.requests.CalculatePaymentRequest;
import com.simple.interest.services.PaymentService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PaymentService service;

    @Test
    public void whenValidInput_amountIsNull_thenReturns400() throws Exception {

        CalculatePaymentRequest request = new CalculatePaymentRequest();
        request.setAmount(null);
        request.setRate(.16D);
        request.setTerms(5);

        this.mockMvc.perform(post("/calculate-payment")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]", Matchers.containsString("amount: must not be null")));
    }

    @Test
    public void whenValidInput_rateIsNull_thenReturns400() throws Exception {

        CalculatePaymentRequest request = new CalculatePaymentRequest();
        request.setAmount(100D);
        request.setRate(null);
        request.setTerms(5);

        this.mockMvc.perform(post("/calculate-payment")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]", Matchers.containsString("rate: must not be null")));
    }

    @Test
    public void whenValidInput_termsIsNull_thenReturns400() throws Exception {

        CalculatePaymentRequest request = new CalculatePaymentRequest();
        request.setAmount(100D);
        request.setRate(.16D);
        request.setTerms(null);

        this.mockMvc.perform(post("/calculate-payment")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]", Matchers.containsString("terms: must not be null")));
    }

    @Test
    public void whenValidInput_amountIsMinorThan1orAmountIsMajorThan999999_thenReturns400() throws Exception {

        CalculatePaymentRequest request = new CalculatePaymentRequest();
        request.setAmount(0D);
        request.setRate(.16D);
        request.setTerms(5);

        this.mockMvc.perform(post("/calculate-payment")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]", Matchers.containsString("amount: must be greater than or equal to 1")));

        request.setAmount(1000000D);

        this.mockMvc.perform(post("/calculate-payment")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]", Matchers.containsString("amount: must be less than or equal to 999999")));
    }

    @Test
    public void whenValidInput_termsIsMinorThan4orTermsIsMajorThan52_thenReturns400() throws Exception {

        CalculatePaymentRequest request = new CalculatePaymentRequest();
        request.setAmount(110D);
        request.setRate(.16D);
        request.setTerms(3);

        this.mockMvc.perform(post("/calculate-payment")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]", Matchers.containsString("terms: must be greater than or equal to 4")));

        request.setTerms(53);

        this.mockMvc.perform(post("/calculate-payment")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]", Matchers.containsString("terms: must be less than or equal to 52")));
    }

    @Test
    public void whenValidInput_rateIsMinorThan0orRateIsMajorThan1_thenReturns400() throws Exception {

        CalculatePaymentRequest request = new CalculatePaymentRequest();
        request.setAmount(110D);
        request.setRate(-1D);
        request.setTerms(5);

        this.mockMvc.perform(post("/calculate-payment")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]", Matchers.containsString("rate: must be greater than or equal to 0")));

        request.setRate(1.01D);

        this.mockMvc.perform(post("/calculate-payment")
                        .content(mapper.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]", Matchers.containsString("rate: must be less than or equal to 1")));
    }


    @Test
    public void whenValidInput_thenReturns200() throws Exception {

        CalculatePaymentRequest request = new CalculatePaymentRequest();
        request.setAmount(100D);
        request.setRate(.16D);
        request.setTerms(5);

        this.mockMvc.perform(post("/calculate-payment")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
