package com.simple.interest.controllers.requests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CalculatePaymentRequest implements Serializable {

    @Min(1)
    @Max(999999)
    @NotNull
    private Double amount;

    @Min(4)
    @Max(52)
    @NotNull
    private Integer terms;

    @Min(0)
    @Max(1)
    @NotNull
    private Double rate;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getTerms() {
        return terms;
    }

    public void setTerms(Integer terms) {
        this.terms = terms;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CalculatePaymentRequest{" +
                "amount=" + amount +
                ", terms=" + terms +
                ", rate=" + rate +
                '}';
    }
}
