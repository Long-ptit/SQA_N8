package com.example.cong.entitis;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int amount;
    private int price;
    private int discount;
    private int totalPrice;

    @ManyToOne()
    @JoinColumn(name = "id_goods")
    private Goods goods;

    @ManyToOne()
    @JoinColumn(name = "id_bill")
    private Bill bill;
}
