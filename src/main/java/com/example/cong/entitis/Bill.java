package com.example.cong.entitis;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tblHoaDon")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String date;
    private int totalAmount;
    private int discount;
    private int totalPrice;
    private int actualPrice;
    private int coinsPay;
    private int pricePay;
    private int priceBack;
    private int isActive;
    @OneToOne
    @JoinColumn(name = "id_staff")//name="tên cột khóa ngoại"
    Staff staff;
    @OneToOne
    @JoinColumn(name = "id_customer")//name="tên cột khóa ngoại"
    Customer customer;

    public Bill() {
    }
}
