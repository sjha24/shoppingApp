package com.HyperSrot.Shopping.App.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private int quantity;
    private double amount;
    private String coupon;
    private String transactionId;
    private String status;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToOne
    @JsonIgnore
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    @JsonIgnore
    private Coupon coupons;


    public Order(int qty, double totalAmount, String couponCode) {
        this.quantity = qty;
        this.amount = totalAmount;
        this.coupon = couponCode;
    }
}
