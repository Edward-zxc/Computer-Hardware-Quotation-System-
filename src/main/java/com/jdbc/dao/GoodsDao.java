package com.jdbc.dao;

import com.jdbc.beans.Goods;

import java.util.List;

public interface GoodsDao {
    List<Goods> findAll();
    Goods findById(int id);
    int deleteGoodsById(int id);
    int addGoods(Goods goods);
    int updateGoods(Goods goods);
}
