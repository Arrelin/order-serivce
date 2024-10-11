package com.arrelin.order.service;

import com.arrelin.order.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.arrelin.order.model.Order;
import org.springframework.stereotype.Service;
import com.arrelin.order.repository.OrderRepository;


import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private  final OrderRepository orderRepository;


    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setSkuCode(orderRequest.skuCode());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());

        orderRepository.save(order);
    }
}
