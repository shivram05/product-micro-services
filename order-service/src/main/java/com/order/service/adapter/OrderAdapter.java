package com.order.service.adapter;

import com.order.service.dto.OrderDTO;
import com.order.service.dto.OrderLineItemsDTO;
import com.order.service.model.Order;
import com.order.service.model.OrderLineItems;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter {

    public static OrderDTO getOrderDTOFromOrder(Order order){
        if(order == null) return null;

        OrderLineItemsDTO orderLineItems = new OrderLineItemsDTO();
        orderLineItems.setId(order.getOrderLineItemsList().listIterator().next().getId());
        orderLineItems.setPrice(order.getOrderLineItemsList().listIterator().next().getPrice());
        orderLineItems.setQuantity(order.getOrderLineItemsList().listIterator().next().getQuantity());
        orderLineItems.setSkuCode(order.getOrderLineItemsList().listIterator().next().getSkuCode());
        List<OrderLineItemsDTO> orderLineItemsList = new ArrayList<>();
        orderLineItemsList.add(orderLineItems);

        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setId(order.getId());
//        orderDTO.setOrderNumber(order.getOrderNumber());
        orderDTO.setOrderLineItemsList(orderLineItemsList);
        return orderDTO;
    }

    public static Order getOrderFromOrderDTO(OrderDTO orderDTO){
        if(orderDTO == null) return null;
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setId(orderDTO.getOrderLineItemsList().listIterator().next().getId());
        orderLineItems.setPrice(orderDTO.getOrderLineItemsList().listIterator().next().getPrice());
        orderLineItems.setQuantity(orderDTO.getOrderLineItemsList().listIterator().next().getQuantity());
        orderLineItems.setSkuCode(orderDTO.getOrderLineItemsList().listIterator().next().getSkuCode());
        List<OrderLineItems> orderLineItemsList = new ArrayList<>();
        orderLineItemsList.add(orderLineItems);

        Order order = new Order();
//        order.setId(orderDTO.getId());
//        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setOrderLineItemsList(orderLineItemsList);
        return order;
    }

    public static List<OrderDTO> getOrderDTOListFromOrderList (List<Order> orderList){
        if(orderList == null) return null;
        return orderList.stream().map(OrderAdapter::getOrderDTOFromOrder).toList();
    }

    public static List<Order> getOrderListFromOrderDTOList (List<OrderDTO> orderDTOList){
        if(orderDTOList == null) return null;
        return orderDTOList.stream().map(OrderAdapter::getOrderFromOrderDTO).toList();
    }
}
