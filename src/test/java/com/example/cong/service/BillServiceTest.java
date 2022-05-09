package com.example.cong.service;

import com.example.cong.entitis.Bill;
import com.example.cong.entitis.CartItem;
import com.example.cong.entitis.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BillServiceTest {
    @Autowired
    BillService billService;

    @Autowired
    StaffService staffService;

    @Autowired
    CustomerService customerService;

    @Test
    @Rollback
    void testSaveItem() {
        Bill bill = new Bill();
        bill.setCoinsPay(0);
        bill.setDiscount(0);
        bill.setPricePay(1000);
        bill.setPriceBack(500);
        bill.setTotalAmount(1);
        bill.setCustomer(customerService.getCustomerById(1));
        bill.setStaff(staffService.getStaffById(2));
        bill.setDate("2022/05/06 11:38:01");
        Bill item = billService.saveItem(bill);
        Bill itemExpected = billService.getBillByIdBill(item.getId());
        assertEquals(itemExpected, item);
    }

    @Test
    void testGetAllBills() {
        int expectedSize = 10;
        //trong database có 11 bản ghi hóa đơn, có 1 bản ghi có is_active = 0
        assertEquals(expectedSize, billService.getAllBills().size());
    }

    @Test
    void testGetBillByIdBillSuccess() {
        Bill bill = new Bill();
        bill.setId(1);
        assertEquals(bill.getId(), billService.getBillByIdBill(1).getId());
    }

    @Test
    void testGetBillByIdBillFailure() {
        assertNull(billService.getBillByIdBill(-1));
    }

    @Test
    void getBillsByIDCustomer() {
    }

    @Test
    void deleteBill() {
    }

    @Test
    void getPriceAfterSale() {
    }

    @Test
    void getCoinAftefSaveBll() {
    }
}