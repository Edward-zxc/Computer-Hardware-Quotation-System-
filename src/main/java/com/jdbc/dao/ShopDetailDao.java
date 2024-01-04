package com.jdbc.dao;

import com.jdbc.beans.ShopDetail;

import java.util.List;

public interface ShopDetailDao {
    List<ShopDetail> findAllShopDetailByUid(int uid);
    int addShopDetail(ShopDetail shopDetail);
}
