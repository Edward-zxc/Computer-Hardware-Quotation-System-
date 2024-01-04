package com.jdbc.dao.USersDaoImpl;

import com.jdbc.beans.Goods;
import com.jdbc.dao.GoodsDao;
import com.jdbc.utils.JDBCUtils;

import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    JDBCUtils jdbcUtils = new JDBCUtils();
    @Override
    public List<Goods> findAll() {
        return jdbcUtils.executeQuery(Goods.class, "select * from goods");
    }

    @Override
    public Goods findById(int id) {
        return jdbcUtils.findOneInstance(Goods.class, "select * from goods where gid = ?", id);
    }

    @Override
    public int deleteGoodsById(int id) {
        return jdbcUtils.executeUpdate("delete from goods where gid = ?", id);
    }

    @Override
    public int addGoods(Goods goods) {
        return jdbcUtils.executeUpdate("insert into goods values(null,?,?,?,?)", goods.getGname(), goods.getGnum(), goods.getGprice(), goods.getTypeid());
    }

    @Override
    public int updateGoods(Goods goods) {
        return jdbcUtils.executeUpdate("update goods set gname = ?, gnum = ?, gprice = ?, typeid = ? where gid = ?", goods.getGname(), goods.getGnum(), goods.getGprice(), goods.getTypeid(), goods.getGid());
    }
}
