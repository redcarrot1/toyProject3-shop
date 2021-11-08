package com.example.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="item-service") //eureka에 등록된 applicaton name
public interface ItemServiceClient {
    @PostMapping("/item/{itemId}/{count}")
    void reduceItemStock(@PathVariable("itemId") Long itemId, @PathVariable("count") Integer count);
}
