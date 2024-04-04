package com.HyperSrot.Shopping.App.DTO;

import lombok.Data;

@Data
public class InventoryResponse {
    private int ordered;
    private int price;
    private int available;
}
