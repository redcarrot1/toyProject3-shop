package com.example.cartservice.controller;

import com.example.cartservice.client.form.ResponseOrder;
import com.example.cartservice.entity.Cart;
import com.example.cartservice.form.*;
import com.example.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService service;

    @PostMapping
    public ResponseCart insertCart(@RequestBody RequestCart form){
        Cart saveCart = service.save(form);
        return new ResponseCart(saveCart.getCartId());
    }

    @GetMapping("/{memberId}")
    public List<ResponseGetCart> findCartByMemberId(@PathVariable Long memberId){
        List<Cart> findCarts = service.findCartsByMemberId(memberId);
        List<ResponseGetCart> result = new ArrayList<>();
        findCarts.forEach(e-> result.add(new ResponseGetCart(e.getCount(), e.getItemId())));

        return result;
    }

    @DeleteMapping("/{cartId}")
    public ResponseDelete deleteCartByCartId(@PathVariable Long cartId) {
        boolean result = service.delete(cartId);
        return new ResponseDelete(result);
    }

    @PatchMapping("/{cartId}")
    public ResponseCart patchCartByCartId(@PathVariable Long cartId, @RequestBody RequestCart form) {
        Cart patchCart = service.patchCartByCartId(cartId, form);
        return new ResponseCart(patchCart.getCartId());
    }


    @PostMapping("/order")
    public ResponseOrder orderItemInCart(@RequestBody RequestOrderInCart form) {
        return service.orderItemInCart(form);
    }
}
