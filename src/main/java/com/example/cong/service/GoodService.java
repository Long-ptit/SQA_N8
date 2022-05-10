package com.example.cong.service;

import com.example.cong.entitis.Goods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public interface GoodService {

    List<Goods> getListGood();
    Goods getGoodById(int id);
    Goods saveGood(Goods good);

}
