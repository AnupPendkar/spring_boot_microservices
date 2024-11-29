package com.anup.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anup.inventory_service.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, int quantity);
}
