package com.jdbc.dao.USersDaoImpl;

import com.jdbc.beans.AdminLog;
import com.jdbc.dao.AdminLogDao;
import com.jdbc.utils.JDBCUtils;

import java.util.List;

public class AdminLogDaoImpl implements AdminLogDao {
    JDBCUtils jdbcUtils = new JDBCUtils();
    @Override
    public int addDesc(AdminLog adminLog) {
        return jdbcUtils.executeUpdate("insert into adminlog values(null,?,?,now())", adminLog.getAid() ,adminLog.getDesc());
    }

    @Override
    public List<AdminLog> findAllLogs() {
        return jdbcUtils.executeQuery(AdminLog.class, "select * from adminlog");
    }
}
