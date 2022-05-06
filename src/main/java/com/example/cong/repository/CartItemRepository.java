package com.example.cong.repository;

import com.example.cong.entitis.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Transactional
    @Query(value = "select * from cart_item where id_bill = ?1", nativeQuery = true)
    List<CartItem> getCartItemByIdBill(int id);
}
