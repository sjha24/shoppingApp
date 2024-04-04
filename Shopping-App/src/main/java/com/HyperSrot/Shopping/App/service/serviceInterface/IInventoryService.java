package com.HyperSrot.Shopping.App.service.serviceInterface;

import com.HyperSrot.Shopping.App.DTO.InventoryResponse;
import com.HyperSrot.Shopping.App.model.Inventory;
import org.springframework.stereotype.Service;

@Service
public interface IInventoryService {
    InventoryResponse getInventoryStatus();

    boolean checkInventoryAvailability(int qty);

    double getPricePerUnit();

    void deductInventory(int qty);
}
