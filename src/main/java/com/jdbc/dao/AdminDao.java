package com.jdbc.dao;

import com.jdbc.beans.Admin;

public interface AdminDao {
    Admin findAdminByNameAndPwd(String name, String pwd);
}
