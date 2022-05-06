package com.example.cong.service.impl;

import com.example.cong.entitis.CartItem;
import com.example.cong.repository.CartItemRepository;
import com.example.cong.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemImpl implements CartItemService {

    @Autowired
    CartItemRepository repository;

    @Override
    public void saveItem(CartItem item) {
        repository.save(item);
    }

    @Override
    public List<CartItem> getCartItemByIdBill(int id) {
        return repository.getCartItemByIdBill(id);
    }
}
