package com.jdbc.dao.USersDaoImpl;

import com.jdbc.beans.ShopDetail;
import com.jdbc.dao.ShopDetailDao;
import com.jdbc.utils.JDBCUtils;

import java.util.List;

/**
 * @author 33133
 */
public class ShopDetailDaoImpl implements ShopDetailDao {
    JDBCUtils jdbcUtils = new JDBCUtils();
    @Override
    public List<ShopDetail> findAllShopDetailByUid(int uid) {
        return jdbcUtils.executeQuery(ShopDetail.class, "select * from shopdetail where uid = ?", uid);
    }

    @Override
    public int addShopDetail(ShopDetail shopDetail) {
        return jdbcUtils.executeUpdate("insert into shopdetail values(null,?,?,now())", shopDetail.getUid(), shopDetail.getCost());
    }
}
