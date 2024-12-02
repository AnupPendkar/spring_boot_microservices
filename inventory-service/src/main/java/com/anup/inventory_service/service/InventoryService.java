package com.anup.inventory_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anup.inventory_service.dto.InventoryRequest;
import com.anup.inventory_service.dto.InventoryResponse;
import com.anup.inventory_service.model.Inventory;
import com.anup.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity() > 0)
                        .build())
                .toList();
    }

    @Transactional
    public void addToInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setSkuCode(inventoryRequest.getSkuCode());
        inventory.setQuantity(inventoryRequest.getQuantity());
        inventoryRepository.save(inventory);
    }

    @Transactional
    public void updateInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = inventoryRepository.findBySkuCode(inventoryRequest.getSkuCode())
                .orElseThrow(() -> new RuntimeException("Inventory not found with SKU: " + inventoryRequest.getSkuCode()));
        
        inventory.setQuantity(inventoryRequest.getQuantity());
        inventoryRepository.save(inventory);
    }
}
