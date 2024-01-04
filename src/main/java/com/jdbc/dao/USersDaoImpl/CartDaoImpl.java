package com.jdbc.dao.USersDaoImpl;

import com.jdbc.beans.Cart;
import com.jdbc.dao.CartDao;
import com.jdbc.utils.JDBCUtils;

import java.util.List;

public class CartDaoImpl implements CartDao {
    JDBCUtils jdbcUtils = new JDBCUtils();
    @Override
    public Cart findByUidAndGid(Integer uid, Integer gid) {
        return jdbcUtils.findOneInstance(Cart.class, "select * from cart where userid = ? and goodsid = ?", uid, gid);
    }

    @Override
    public int save(Cart cart) {
        return jdbcUtils.executeUpdate("insert into cart values(null,?,?,?)", cart.getGoodsid(), cart.getCnum(), cart.getUserid());
    }

    @Override
    public int update(Cart cart) {
        return jdbcUtils.executeUpdate("update cart set cnum = cnum + 1 where cid = ?", cart.getCid());
    }

    @Override
    public int delete(int id) {
        return jdbcUtils.executeUpdate("delete from cart where cid = ?", id);
    }

    @Override
    public List<Cart> findAllByUid(Integer uid) {
        String sql = "select c.*, g.gname,g.gprice from cart c inner join goods g on c.goodsid = g.gid where userid = ?";
        return jdbcUtils.executeQuery(Cart.class, sql, uid);
    }
}
