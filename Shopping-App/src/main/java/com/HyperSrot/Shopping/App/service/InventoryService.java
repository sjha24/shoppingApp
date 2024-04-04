package com.HyperSrot.Shopping.App.service;

import com.HyperSrot.Shopping.App.DTO.InventoryResponse;
import com.HyperSrot.Shopping.App.configuration.InventoryProperties;
import com.HyperSrot.Shopping.App.model.Inventory;
import com.HyperSrot.Shopping.App.service.serviceInterface.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService implements IInventoryService {

    private final Inventory inventory;

    @Autowired
    public InventoryService(InventoryProperties inventoryProperties) {
        // Initialize the inventory based on the properties file
        this.inventory = new Inventory(1L, inventoryProperties.getPrice(), inventoryProperties.getInitialQuantity());
    }

    public InventoryResponse getInventoryStatus() {
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setOrdered(inventory.getOrdered());
        inventoryResponse.setAvailable(inventory.getAvailable());
        inventoryResponse.setPrice(inventory.getPrice());
        return inventoryResponse;
    }

    @Override
    public boolean checkInventoryAvailability(int qty) {
        return inventory.getAvailable() >= qty;
    }

    @Override
    public double getPricePerUnit() {
        return inventory.getPrice();
    }

    @Override
    public void deductInventory(int qty) {
        int availableQty = inventory.getAvailable();
        int deducedQuantity = availableQty - qty;
        inventory.setAvailable(deducedQuantity);
        inventory.setOrdered(inventory.getOrdered()+qty);
    }
}
