package com.HyperSrot.Shopping.App.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long orderId;
    @JsonInclude(JsonInclude.Include.NON_NULL)// This field is omitted if null
    private Long userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer quantity;
    private double amount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String coupon;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transactionId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;

    public OrderResponse(long orderId, long userId, int quantity, double amount, String couponCode) {
        this.orderId = orderId;
        this.userId = userId;
        this.quantity = quantity;
        this.amount = amount;
        this.coupon = couponCode;
    }

}
