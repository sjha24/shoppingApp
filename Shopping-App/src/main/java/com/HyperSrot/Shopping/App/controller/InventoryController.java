package com.HyperSrot.Shopping.App.controller;

import com.HyperSrot.Shopping.App.DTO.InventoryResponse;
import com.HyperSrot.Shopping.App.model.Inventory;
import com.HyperSrot.Shopping.App.service.InventoryService;
import com.HyperSrot.Shopping.App.service.serviceInterface.IInventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Inventory")
@Tag(name = "Inventory Management")
public class InventoryController {
    @Autowired
    private IInventoryService inventoryService;

    @Operation(summary = "Retrieves the current status of the inventory, including the available quantities of products")
    @ApiResponse(responseCode = "200",description = "Successfully retrieved the inventory status. Returns details of the inventory including product names and their available quantities")
    @GetMapping
    public InventoryResponse getInventoryStatus() {
        return inventoryService.getInventoryStatus();
    }


}
