package com.order.service.controller;

import com.order.service.dto.InventoryDTO;
import com.order.service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;


    /*
    * for the request params
    * http://localhost:8082/api/inventory?sku-code=iphone-13&sku-code=iphone13-red
    * */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDTO> isInStock(@RequestParam List<String> skuCode){

        return inventoryService.isInStock(skuCode);
    }

}
