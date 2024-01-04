package com.jdbc.dao.USersDaoImpl;

import com.jdbc.beans.Admin;
import com.jdbc.dao.AdminDao;
import com.jdbc.utils.JDBCUtils;

/**
 * @author 33133
 */
public class AdminDaoImpl implements AdminDao {
    JDBCUtils jdbcUtils = new JDBCUtils();
    @Override
    public Admin findAdminByNameAndPwd(String name, String pwd) {
        return jdbcUtils.findOneInstance(Admin.class, "select * from admin where username = ? and password = ?", name, pwd);
    }
}
