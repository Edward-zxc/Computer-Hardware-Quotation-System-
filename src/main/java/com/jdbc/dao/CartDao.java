package com.jdbc.dao;

import com.jdbc.beans.Cart;

import java.util.List;

public interface CartDao {
    Cart findByUidAndGid(Integer uid, Integer gid);
    int save(Cart cart);
    int update(Cart cart);
    int delete(int id);
    List<Cart> findAllByUid(Integer uid);
}
