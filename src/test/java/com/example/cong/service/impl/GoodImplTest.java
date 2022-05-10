package com.example.cong.service.impl;

import com.example.cong.entitis.Goods;
import com.example.cong.repository.GoodRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class GoodImplTest {

    @Autowired
    GoodRepository goodRepository;

    @Autowired
    GoodImpl goodImp;

    @Test
    @Transactional
    @Rollback
    void testSaveGood() {
        Goods goods = new Goods();
        goods.setName("dau xanh");
        goods.setPrice(10);
        goods.setDiscount(0);
        goods.setIsActive(1);
        Goods goods1 =  goodImp.saveGood(goods);
        assertEquals(8, goods1.getId());
    }


    @Test
    void testGetListGood() {
        int expectedSize = 6;
        assertEquals(expectedSize, goodImp.getListGood().size());
    }

    @Test
    void getGoodById() {
    }




}