package com.example.cartservice.client;

import com.example.cartservice.client.form.ResponseItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="item-service") //eureka에 등록된 applicaton name
public interface ItemServiceClient {
    @GetMapping("/item/{itemId}")
    ResponseItem itemByItemId(@PathVariable Long itemId);
}
