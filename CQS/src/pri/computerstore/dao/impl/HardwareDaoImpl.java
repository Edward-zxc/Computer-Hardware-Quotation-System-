package pri.computerstore.dao.impl;

import pri.computerstore.dao.HardwareDao;
import pri.computerstore.model.Hardware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 33133
 */
public class HardwareDaoImpl implements HardwareDao {
    private final Connection connection;

    public HardwareDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Hardware getHardwareById(int id) {
        Hardware hardware = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM computerhardware WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                hardware = new Hardware();
                hardware.setId(resultSet.getInt("id"));
                hardware.setName(resultSet.getString("name"));
                hardware.setPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 实际应用中应该处理异常
        }
        return hardware;
    }

    @Override
    public void saveHardware(Hardware newHardware) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO hardware (Brand, Price) VALUES (?, ?)")) {
            statement.setString(1, Hardware.getName());
            statement.setDouble(2, Hardware.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // 实际应用中应该处理异常
        }
    }

    // 可以根据需要添加其他与硬件相关的数据库操作方法
}
