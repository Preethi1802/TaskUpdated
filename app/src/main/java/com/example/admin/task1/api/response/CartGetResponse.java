package com.example.admin.task1.api.response;

import com.example.admin.task1.model.Cart;

import java.util.List;

/**
 * Created by Admin on 9/14/2017.
 */

public class CartGetResponse extends GenericResponse
{
    private int cartCount;
    private List<Cart> cart;

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

}
