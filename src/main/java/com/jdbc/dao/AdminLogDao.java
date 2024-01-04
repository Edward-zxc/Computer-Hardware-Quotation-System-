package com.jdbc.dao;

import com.jdbc.beans.AdminLog;

import java.util.List;

public interface AdminLogDao {
    int addDesc(AdminLog adminLog);
    List<AdminLog> findAllLogs();
}
