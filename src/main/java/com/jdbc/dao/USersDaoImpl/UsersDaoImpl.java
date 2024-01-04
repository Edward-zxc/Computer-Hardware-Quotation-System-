package com.jdbc.dao.USersDaoImpl;

import com.jdbc.beans.Users;

import com.jdbc.dao.UserDao;
import com.jdbc.utils.JDBCUtils;

import java.util.List;

public class UsersDaoImpl implements UserDao {
    JDBCUtils jdbcUtils = new JDBCUtils();
    @Override
    public Users findUserByNameAndPwd(String name, String pwd) {
        return jdbcUtils.findOneInstance(Users.class, "select * from users where uname = ? and upwd = ?", name, pwd);
    }

    @Override
    public Users findUserByName(String name) {
        return jdbcUtils.findOneInstance(Users.class, "select * from users where uname = ?", name);
    }

    @Override
    public Users findUserById(int id) {
        return jdbcUtils.findOneInstance(Users.class, "select * from users where id = ?", id);
    }

    @Override
    public int deleteUsers(int id) {
        return jdbcUtils.executeUpdate("delete from users where id = ?", id);
    }

    @Override
    public int updateUsers(Users users) {
        return jdbcUtils.executeUpdate("update users set uname = ?, upwd = ?, ustatus = ?, uscore = ? where id = ?", users.getUname(), users.getUpwd(), users.getUstatus(), users.getUscore(), users.getId());
    }

    @Override
    public List<Users> findAll() {
        return jdbcUtils.executeQuery(Users.class, "select * from users");
    }

    @Override
    public int addUsers(Users users) {
        return jdbcUtils.executeUpdate("insert into users values(null,?,?,?,0)", users.getUname(), users.getUpwd(), users.getUstatus());
    }
}
