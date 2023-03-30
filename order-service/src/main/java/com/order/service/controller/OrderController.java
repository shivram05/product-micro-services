package com.order.service.controller;

import com.order.service.dto.OrderDTO;
import com.order.service.dto.OrderLineItemsDTO;
import com.order.service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO requestOrderDTO){
       OrderDTO orderDTO = orderService.createOrder(requestOrderDTO);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrderList(){
        List<OrderLineItemsDTO> orderDTOList = orderService.getAllOrder().listIterator().next().getOrderLineItemsList();
        return new ResponseEntity<>(orderDTOList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getOrderById(@PathVariable(name = "id") long id){

        OrderDTO orderDTO = orderService.getOrderId(id);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);

    }

}
