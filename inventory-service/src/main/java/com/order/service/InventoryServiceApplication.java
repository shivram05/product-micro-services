package com.order.service;

import com.order.service.model.Inventory;
import com.order.service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryServiceApplication implements CommandLineRunner{

    @Autowired
    private InventoryRepository inventoryRepository;
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        Inventory inventory = new Inventory();
        inventory.setSkuCode("iphone-13");
        inventory.setQuantity(5);

        Inventory inventoryTwo = new Inventory();
        inventoryTwo.setSkuCode("iphone-13-red");
        inventoryTwo.setQuantity(0);

        inventoryRepository.save(inventory);
        inventoryRepository.save(inventoryTwo);
    }
}
