package com.example.cong.service.impl;

import com.example.cong.entitis.Goods;
import com.example.cong.repository.GoodRepository;
import com.example.cong.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodImpl implements GoodService {

    @Autowired
    GoodRepository goodRepository;

    @Override
    public List<Goods> getListGood() {
        return goodRepository.findAll();
    }

    @Override
    public Goods getGoodById(int id) {
        return goodRepository.getById(id);
    }
}
