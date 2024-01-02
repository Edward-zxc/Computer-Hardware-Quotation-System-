package pri.computerstore.util;

import java.sql.*;

/**
 * @author 33133
 */
public class JDBCUtils {
    private static Connection conn;

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/computerised quotation system (cqs)?useSSL=false&characterEncoding=UTF-8";
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
    }
        return conn;
}
public static void main(String[] args) {
        conn = JDBCUtils.getConnection();
        System.out.println(conn);
    }
}