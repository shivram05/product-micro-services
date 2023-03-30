package com.order.service.service;

import com.order.service.adapter.InventoryAdapter;
import com.order.service.dto.InventoryDTO;
import com.order.service.model.Inventory;
import com.order.service.repository.InventoryRepository;
import com.order.service.utils.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j

public class InventoryServiceImpl implements InventoryService{
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryDTO> isInStock(List<String> skuCode){

       return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(this::mapInventoryDTO).collect(Collectors.toList());
    }

    public InventoryDTO mapInventoryDTO(Inventory inventory){

        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setSkuCode(inventory.getSkuCode());
        inventoryDTO.setIStock(inventory.getQuantity()>0);
        return inventoryDTO;
    }

    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(inventory.getQuantity());
        inventory.setSkuCode(inventory.getSkuCode());
        inventoryRepository.save(inventory);
        return InventoryAdapter.getInventoryDTOFromInventory(inventory);
    }

    @Override
    public List<InventoryDTO> getAllInventory() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        return InventoryAdapter.getInventoryDTOListFromInventoryList(inventoryList);
    }

    @Override
    public InventoryDTO getInventoryId(long id) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        Inventory inventory =null;
        if (optionalInventory.isPresent()){
            inventory = optionalInventory.get();
        }else {
            throw new ApiException("Order with id = " + id  + " cannot found", HttpStatus.NOT_FOUND);
        }
        return InventoryAdapter.getInventoryDTOFromInventory(inventory);
    }

    @Override
    public InventoryDTO updateInventory(long id, InventoryDTO inventoryDTO) {
        return null;
    }

    @Override
    public void deleteInventory(long id) {

    }
}
