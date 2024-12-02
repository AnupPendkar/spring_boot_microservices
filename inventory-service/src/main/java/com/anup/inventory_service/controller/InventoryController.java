package com.anup.inventory_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.anup.inventory_service.dto.InventoryRequest;
import com.anup.inventory_service.dto.InventoryResponse;
import com.anup.inventory_service.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addInventory(@RequestBody InventoryRequest inventoryRequest) {
        inventoryService.addToInventory(inventoryRequest);
        return "Inventory added successfully";
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public String updateInventory(@RequestBody InventoryRequest inventoryRequest) {
        inventoryService.updateInventory(inventoryRequest);
        return "Inventory updated successfully";
    }
}
