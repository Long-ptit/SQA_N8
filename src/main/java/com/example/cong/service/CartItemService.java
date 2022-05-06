package com.example.cong.service;

import com.example.cong.entitis.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartItemService {

    void saveItem(CartItem item);
    List<CartItem> getCartItemByIdBill(int id);
}
