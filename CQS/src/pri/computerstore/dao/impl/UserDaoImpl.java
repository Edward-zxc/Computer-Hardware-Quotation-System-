package pri.computerstore.dao.impl;

import pri.computerstore.dao.UserDao;
import pri.computerstore.model.User;
import pri.computerstore.model.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 33133
 */
public class UserDaoImpl implements UserDao {
    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User registerUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, role) VALUES (?, ?, ?)")) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // 实际应用中应该处理异常
        }
        return user;
    }

    @Override
    public User loginUser(String username, String password) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(UserRole.valueOf(resultSet.getString("role")));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 实际应用中应该处理异常
        }
        return null;
    }

    // 可以根据需要添加其他与用户相关的数据库操作方法
}
