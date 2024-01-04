package com.jdbc.dao;

import com.jdbc.beans.Users;

import java.util.List;

public interface UserDao {
    Users findUserByNameAndPwd(String name, String pwd);
    Users findUserByName(String name);
    Users findUserById(int id);
    int deleteUsers(int id);
    int updateUsers(Users users);
    List<Users> findAll();
    int addUsers(Users users);
}
