package com.HyperSrot.Shopping.App.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    private Long id;
    private int ordered;
    private int price;
    private int available;

    public Inventory(Long id, int price, int initialQuantity) {
        this.id = id;
        this.price = price;
        this.available = initialQuantity;
    }
}
