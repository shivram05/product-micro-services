package com.order.service.service;

import com.order.service.adapter.OrderAdapter;
import com.order.service.dto.OrderDTO;
import com.order.service.dto.OrderLineItemsDTO;
import com.order.service.model.Order;
import com.order.service.model.OrderLineItems;
import com.order.service.repository.OrderRepository;
import com.order.service.utils.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient webClient;
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderDTO.getOrderLineItemsList()
                        .stream().map(this::mapToDTO).collect(Collectors.toList());
        order.setOrderLineItemsList(orderLineItemsList);


        log.info("order " + order.getId() + "is saved");


        /*
        * call inventory service and place order if product is in stock
        * */

//        read data from the client response as mono
        Boolean result = webClient.get().uri("http://localhost:8082/api/inventories").retrieve().bodyToMono(Boolean.class).block();

        if (result){
            orderRepository.save(order);
        }else {
            throw new ApiException("Product is not in stock, Please try again", HttpStatus.NOT_FOUND);
        }
        return OrderAdapter.getOrderDTOFromOrder(order);
    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
        return orderLineItems;
    }

    @Override
    public List<OrderDTO> getAllOrder() {
        List<Order> orderList = orderRepository.findAll();
        return OrderAdapter.getOrderDTOListFromOrderList(orderList);
    }

    @Override
    public OrderDTO getOrderId(long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        Order order =null;
        if (optionalOrder.isPresent()){
            order = optionalOrder.get();
        }else {
            throw new ApiException("Order with id = " + id  + " cannot found", HttpStatus.NOT_FOUND);
        }
        return OrderAdapter.getOrderDTOFromOrder(order);
    }

    @Override
    public OrderDTO updateOrder(long id, OrderDTO productDTO) {
        return null;
    }

    @Override
    public void deleteOrder(long id) {

    }
}
