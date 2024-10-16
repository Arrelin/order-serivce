package com.arrelin.order.service;

import com.arrelin.order.client.InventoryClient;
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
    private final InventoryClient inventoryClient;


    public void placeOrder(OrderRequest orderRequest) {
        log.info("Checking for stock availability for product with SkuCode: '{}' and quantity of '{}'"
                , orderRequest.skuCode(), orderRequest.quantity());
        var isProductInStock = inventoryClient.isIntStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isProductInStock) {
            log.info("Product with SkuCode: '{}' is in stock. Placing order now...", orderRequest.skuCode());
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());

            orderRepository.save(order);
        } else {
            throw new RuntimeException(String.format("Product with SkuCode %s is not on stock", orderRequest.skuCode()));
        }
    }
}
