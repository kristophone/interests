package com.simple.interest.controllers.responses;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CalculatePaymentResponse implements Serializable {

    private List<Payment> paymentList;

    public static class Payment {

        private final Integer paymentNumber;

        private final Double amount;

        private final Date paymentDate;

        public Payment(Integer paymentNumber, Double amount, Date paymentDate) {
            this.paymentNumber = paymentNumber;
            this.amount = amount;
            this.paymentDate = paymentDate;
        }

        public Integer getPaymentNumber() {
            return paymentNumber;
        }

        public Double getAmount() {
            return amount;
        }

        public Date getPaymentDate() {
            return paymentDate;
        }

        @Override
        public String toString() {
            return "Payment{" +
                    "paymentNumber=" + paymentNumber +
                    ", amount=" + amount +
                    ", paymentDate=" + paymentDate +
                    '}';
        }
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    @Override
    public String toString() {
        return "CalculatePaymentResponse{" +
                "paymentList=" + paymentList +
                '}';
    }
}
