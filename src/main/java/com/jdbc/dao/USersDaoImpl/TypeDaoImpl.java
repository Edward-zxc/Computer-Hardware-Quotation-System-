package com.jdbc.dao.USersDaoImpl;

import com.jdbc.beans.Type;
import com.jdbc.dao.TypeDao;
import com.jdbc.utils.JDBCUtils;

import java.util.List;

public class TypeDaoImpl implements TypeDao {
    JDBCUtils jdbcUtils = new JDBCUtils();
    @Override
    public Type findTypeByName(String name) {
        return jdbcUtils.findOneInstance(Type.class, "select * from goodstype where typename = ?", name);
    }

    @Override
    public Type findTypeById(int id) {
        return jdbcUtils.findOneInstance(Type.class, "select * from goodstype where typeid = ?", id);
    }

    @Override
    public int addType(Type type) {
        return jdbcUtils.executeUpdate("insert into goodstype values(null, ?)", type.getTypename());
    }

    @Override
    public int deleteType(Type type) {
        return jdbcUtils.executeUpdate("delete from goodstype where typeid = ?", type.getTypeid());
    }

    @Override
    public int updateTypeName(Type type) {
        return jdbcUtils.executeUpdate("update goodstype set typename = ? where typeid = ?", type.getTypename(), type.getTypeid());
    }

    @Override
    public List<Type> findAllTypes() {
        return jdbcUtils.executeQuery(Type.class, "select * from goodstype");
    }
}
