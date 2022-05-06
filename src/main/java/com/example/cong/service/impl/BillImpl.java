package com.example.cong.service.impl;

import com.example.cong.entitis.Bill;
import com.example.cong.entitis.Customer;
import com.example.cong.repository.BillRepository;
import com.example.cong.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BillImpl implements BillService {

    @Autowired
    BillRepository repository;

    @Override
    @Transactional
    public Bill saveItem(Bill item) {
        //kích hoạt
        item.setIsActive(1);
        return repository.save(item);
    }

    @Override
    public List<Bill> getAllBills() {
        return repository.getAllBillIsAlive();
    }

    @Override
    public Bill getBillByIdBill(long id) {
        return repository.getById(id);
    }

    @Override
    public List<Bill> getBillsByIDCustomer(long id) {
        return repository.getBillByIDCustomer(id);
    }

    @Override
    public Bill deleteBill(long id) {
        Bill bill = repository.getById(id);
        bill.setIsActive(0);
        return repository.save(bill);
    }
}
