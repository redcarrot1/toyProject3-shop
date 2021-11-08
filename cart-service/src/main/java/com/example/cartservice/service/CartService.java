package com.example.cartservice.service;

import com.example.cartservice.client.ItemServiceClient;
import com.example.cartservice.client.OrderServiceClient;
import com.example.cartservice.client.form.RequestOrder;
import com.example.cartservice.client.form.RequestOrderItem;
import com.example.cartservice.client.form.ResponseItem;
import com.example.cartservice.client.form.ResponseOrder;
import com.example.cartservice.entity.Cart;
import com.example.cartservice.form.RequestCart;
import com.example.cartservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Cart> findByMemberId(Long memberId) {
        return cartRepository.findAllByMemberId(memberId);
    }

    public boolean delete(Long cartId) {
        cartRepository.deleteById(cartId);
        return true;
    }

    public Cart patchCartByCartId(Long cartId, RequestCart form) {
        Optional<Cart> findCartItem = cartRepository.findById(cartId);
        if(findCartItem.isEmpty()){
            //TODO 오류
        }
        Cart cart = findCartItem.get();
        cart.setCount(form.getCount());
        cart.setItemId(form.getItemId());
        return cartRepository.save(cart);
    }

    public ResponseOrder orderItemInCart(Long memberId) {
        List<Cart> carts = cartRepository.findAllByMemberId(memberId);
        List<RequestOrderItem> orderItems = new ArrayList<>();
        carts.forEach(e-> {
            ResponseItem item = itemServiceClient.itemByItemId(e.getItemId());
            //TODO ITEM상태, 재고 등 고려
            log.info("itemPrice={}", item.getPrice());
            orderItems.add(new RequestOrderItem(e.getCount(), item.getPrice() * e.getCount(), e.getItemId()));
            
            cartRepository.deleteById(e.getCartId());
        });

        return orderServiceClient.order(new RequestOrder(memberId, orderItems));
    }
}
