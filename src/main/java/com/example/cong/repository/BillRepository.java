package com.example.cong.repository;

import com.example.cong.entitis.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query(value = "select * from tbl_hoa_don where id_customer = ? and is_active = 1", nativeQuery = true)
    List<Bill> getBillByIDCustomer(long id);

    @Query(value = "select * from tbl_hoa_don where is_active = 1", nativeQuery = true)
    List<Bill> getAllBillIsAlive();
}
