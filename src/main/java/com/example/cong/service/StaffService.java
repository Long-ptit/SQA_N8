package com.example.cong.service;

import com.example.cong.entitis.Staff;
import org.springframework.stereotype.Service;

@Service
public interface StaffService {

    Boolean checkAcount(String username, String password);

    Staff getStaffByUserNameAndPassword(String username, String password);

    Staff getStaffById(int id);

}
