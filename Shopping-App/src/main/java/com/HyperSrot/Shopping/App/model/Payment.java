package com.HyperSrot.Shopping.App.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private Long userId;
    private String transactionId;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)// This field is omitted if null
    private String description;

    @OneToOne
    @JoinColumn(name = "orderId")
    @JsonIgnore
    private Order order;
}
