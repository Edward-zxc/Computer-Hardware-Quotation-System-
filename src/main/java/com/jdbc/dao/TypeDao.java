package com.jdbc.dao;

import com.jdbc.beans.Type;

import java.util.List;

public interface TypeDao {

    Type findTypeByName(String name);
    Type findTypeById(int id);
    int addType(Type type);
    int deleteType(Type type);
    int updateTypeName(Type type);
    List<Type> findAllTypes();
}
