package com.example.cong.repository;

import com.example.cong.entitis.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepository extends JpaRepository<Goods, Integer> {
}
