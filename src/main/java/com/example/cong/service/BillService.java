package com.example.cong.service;

import com.example.cong.entitis.Bill;
import com.example.cong.entitis.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {

    Bill saveItem(Bill item);
    List<Bill> getAllBills();
    Bill getBillByIdBill(long id);
    List<Bill> getBillsByIDCustomer(long id);
    int getPriceAfterSale(Bill bill);
    int getCoinAftefSaveBll(Bill bill, Customer customer);
    int getCoinWhenSave(Bill bill, Customer customer);
}
