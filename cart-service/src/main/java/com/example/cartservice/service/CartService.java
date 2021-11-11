package com.example.cartservice.service;

import com.example.cartservice.client.ItemServiceClient;
import com.example.cartservice.client.OrderServiceClient;
import com.example.cartservice.client.form.RequestOrder;
import com.example.cartservice.client.form.RequestOrderItem;
import com.example.cartservice.client.form.ResponseItem;
import com.example.cartservice.client.form.ResponseOrder;
import com.example.cartservice.entity.Cart;
import com.example.cartservice.error.ApiException;
import com.example.cartservice.error.ExceptionEnum;
import com.example.cartservice.form.RequestCart;
import com.example.cartservice.form.RequestOrderInCart;
import com.example.cartservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final OrderServiceClient orderServiceClient;
    private final ItemServiceClient itemServiceClient;

    public Cart save(RequestCart form) {
        Cart cart = new Cart(form.getCount(), form.getItemId(), form.getMemberId());
        return cartRepository.save(cart);
    }

    public List<Cart> findCartsByMemberId(Long memberId) {
        return cartRepository.findAllByMemberId(memberId);
    }

    public Cart findCartByCartId(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new ApiException(ExceptionEnum.NO_CART_BY_CARTID));
    }

    public boolean delete(Long cartId) {
        Cart findCart = findCartByCartId(cartId);
        cartRepository.delete(findCart);
        return true;
    }

    public Cart patchCartByCartId(Long cartId, RequestCart form) {
        Cart findCart = findCartByCartId(cartId);
        findCart.setCount(form.getCount());
        findCart.setItemId(form.getItemId());
        return cartRepository.save(findCart);
    }

    public ResponseOrder orderItemInCart(RequestOrderInCart form) {
        List<RequestOrderItem> orderItems = new ArrayList<>();

        form.getOrderItems().forEach(e -> {
            orderItems.add(new RequestOrderItem(e.getCount(), e.getOrderPrice(), e.getItemId()));
        });

        ResponseOrder result = orderServiceClient.order(new RequestOrder(form.getMemberId(), orderItems));

        form.getOrderItems().forEach(e -> {
            cartRepository.delete(findCartByCartId(e.getCartId()));
        });

        return result;
    }
}
