package com.example.orderservice.service;

import com.example.orderservice.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
}
