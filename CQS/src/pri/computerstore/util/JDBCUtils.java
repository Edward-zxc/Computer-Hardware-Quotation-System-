package pri.computerstore.util;

import java.sql.*;

/**
 * @author 33133
 */
public class JDBCUtils {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/computer-system?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {

            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connection = JDBCUtils.getConnection();
        System.out.println(connection);

        // 在使用完连接后关闭
        JDBCUtils.closeConnection();
    }
}
