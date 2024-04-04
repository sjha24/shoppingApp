package com.HyperSrot.Shopping.App.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private int discountPercentage;

    @OneToMany(mappedBy = "coupon")
    @JsonIgnore
    private List<Order>orderList;

    public Coupon(String couponCode, int discountPercentage) {
        this.code = couponCode;
        this.discountPercentage = discountPercentage;
    }
}
