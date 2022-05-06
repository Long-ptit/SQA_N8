package com.example.cong.service;

import com.example.cong.entitis.Goods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodService {

    List<Goods> getListGood();
    Goods getGoodById(int id);

}
