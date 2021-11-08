package com.example.cartservice.client;

import com.example.cartservice.client.form.RequestOrder;
import com.example.cartservice.client.form.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="order-service") //eureka에 등록된 applicaton name
public interface OrderServiceClient {
    @PostMapping("/order")
    ResponseOrder order(@RequestBody RequestOrder form);
}
