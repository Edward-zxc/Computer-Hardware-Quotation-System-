package com.jdbc.utils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author 33133
 */
public class JDBCUtils {
    public static String driver;
    public static String url;
    public static String username;
    public static String password;


    static {
        try {
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public String change(String label) {
        String newLabel = label;
        int index = label.indexOf('_');
        if (index != -1) {
            newLabel = label.substring(0, index) + label.substring(index + 1, index + 2).toUpperCase() + label.substring(index + 2);
        }
        return newLabel;
    }

    public int executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = -1;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            setParams(pstmt, params);
            rows = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, null);
        }
        return rows;
    }

    public void setParams(PreparedStatement pstmt, Object... params) {
        try {
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> T findOneInstance(Class<T> clazz, String sql, Object...params) {
        List<T> list = executeQuery(clazz, sql, params);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) {
        List<T> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            setParams(pstmt, params);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                T entity = clazz.newInstance();
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String label = rsmd.getColumnLabel(i);
                    Object val = rs.getObject(label);
                    String type = rsmd.getColumnClassName(i);
                    String newLabel = change(label);
                    Field f = clazz.getDeclaredField(newLabel);
                    f.setAccessible(true);
                    // 对日期类型进行特殊判断
                    if (type.contains("Date")) {
                        Class type2 = f.getType();
                        // 如果类中的成员变量也为Date则直接赋值 否则转换为String再赋值
                        if (type2.toString().contains("Date")) {
                            f.set(entity, val);
                        } else {
                            if (null != val) {
                                Date date = (Date) val;
                                f.set(entity, date.toString());
                            }
                        }
                    } else {
                        f.set(entity, val);
                    }
                }
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }
        return list;
    }
}
