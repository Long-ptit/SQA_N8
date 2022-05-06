package com.example.cong.service.impl;

import com.example.cong.entitis.Staff;
import com.example.cong.repository.StaffRepository;
import com.example.cong.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public Boolean checkAcount(String username, String password) {
        Staff staff = staffRepository.findUser(username, password);

        if(staff!=null){
            return true;
        }
        return false;
    }

    @Override
    public Staff getStaffByUserNameAndPassword(String username, String password) {
        Staff staff = staffRepository.findUser(username, password);
        return staff;
    }

    @Override
    public Staff getStaffById(int id) {
        return staffRepository.getById(id);
    }
}
