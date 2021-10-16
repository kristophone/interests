package com.simple.interest.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Payment> paymentList;

    private Double amount;

    private Integer terms;

    private Double rate;

    private LocalDateTime createdAt;

    @Entity
    @Table(name = "payment")
    public static class Payment {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private Integer paymentNumber;

        private Double amount;

        private LocalDate paymentDate;

        private LocalDateTime createdAt;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getPaymentNumber() {
            return paymentNumber;
        }

        public void setPaymentNumber(Integer paymentNumber) {
            this.paymentNumber = paymentNumber;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public LocalDate getPaymentDate() {
            return paymentDate;
        }

        public void setPaymentDate(LocalDate paymentDate) {
            this.paymentDate = paymentDate;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        @Override
        public String toString() {
            return "Payment{" +
                    "id=" + id +
                    ", paymentNumber=" + paymentNumber +
                    ", amount=" + amount +
                    ", paymentDate=" + paymentDate +
                    ", createdAt=" + createdAt +
                    '}';
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", paymentList=" + paymentList +
                ", amount=" + amount +
                ", terms=" + terms +
                ", rate=" + rate +
                ", createdAt=" + createdAt +
                '}';
    }
}
