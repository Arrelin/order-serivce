package com.arrelin.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "http://localhost:8083")
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
