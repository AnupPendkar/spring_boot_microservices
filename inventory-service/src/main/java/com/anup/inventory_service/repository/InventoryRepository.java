package com.anup.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anup.inventory_service.model.Inventory;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
