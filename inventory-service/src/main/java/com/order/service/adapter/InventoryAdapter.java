package com.order.service.adapter;

import com.order.service.dto.InventoryDTO;
import com.order.service.model.Inventory;

import java.util.List;

public class InventoryAdapter {

    public static InventoryDTO getInventoryDTOFromInventory(Inventory inventory){
        if(inventory == null) return null;

        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setQuantity(inventoryDTO.getQuantity());
        inventoryDTO.setSkuCode(inventory.getSkuCode());
        return inventoryDTO;
    }

    public static Inventory getInventoryFromInventoryDTO(InventoryDTO inventoryDTO){
        if(inventoryDTO == null) return null;
        Inventory inventory = new Inventory();
        inventory.setQuantity(inventoryDTO.getQuantity());
        inventory.setSkuCode(inventory.getSkuCode());
        return inventory;
    }

    public static List<InventoryDTO> getInventoryDTOListFromInventoryList (List<Inventory> inventoryList){
        if(inventoryList == null) return null;
        return inventoryList.stream().map(InventoryAdapter::getInventoryDTOFromInventory).toList();
    }

    public static List<Inventory> getInventoryListFromInventoryDTOList (List<InventoryDTO> inventoryDTOS){
        if(inventoryDTOS == null) return null;
        return inventoryDTOS.stream().map(InventoryAdapter::getInventoryFromInventoryDTO).toList();
    }
}
