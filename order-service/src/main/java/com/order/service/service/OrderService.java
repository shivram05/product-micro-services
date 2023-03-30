package com.order.service.service;

import com.order.service.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder(OrderDTO productDTO);

    List<OrderDTO> getAllOrder();

    OrderDTO getOrderId(long id);

    OrderDTO updateOrder(long id, OrderDTO productDTO);

    void deleteOrder(long id);
}
