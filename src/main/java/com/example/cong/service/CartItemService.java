package com.example.cong.service;

import com.example.cong.entitis.CartItem;
import com.example.cong.entitis.Goods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartItemService {

    CartItem saveItem(CartItem item);
    List<CartItem> getCartItemByIdBill(int id);
    int getSumOfListCart(List<CartItem> list);
    int getSumQuantityCart(List<CartItem> list);
    int getPriceFromCartItem(int quantity, int price);
    boolean checkExistCartItem(List<CartItem> list, Goods goods);
    List<CartItem> handleDeleteCartItem(long id, List<CartItem> list);
}
