package com.order.service.service;

import com.order.service.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {

    InventoryDTO createInventory(InventoryDTO inventoryDTO);

    List<InventoryDTO> getAllInventory();

    InventoryDTO getInventoryId(long id);

    InventoryDTO updateInventory(long id, InventoryDTO inventoryDTO);

    void deleteInventory(long id);

    List<InventoryDTO> isInStock(List<String> skuCode);
}
