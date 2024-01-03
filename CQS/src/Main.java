/**
 * @author 33133
 */

import pri.computerstore.dao.HardwareDao;
import pri.computerstore.dao.UserDao;
import pri.computerstore.dao.impl.HardwareDaoImpl;
import pri.computerstore.dao.impl.UserDaoImpl;
import pri.computerstore.service.AdminService;
import pri.computerstore.service.HardwareService;
import pri.computerstore.service.UserService;
import pri.computerstore.service.impl.AdminServiceImpl;
import pri.computerstore.service.impl.HardwareServiceImpl;
import pri.computerstore.service.impl.UserServiceImpl;
import pri.computerstore.util.JDBCUtils;
import pri.computerstore.model.UserRole;
import pri.computerstore.model.Hardware;
import pri.computerstore.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // 初始化数据库连接
            Connection connection = JDBCUtils.getConnection();

            // 创建 UserDao、UserServiceImpl、AdminService 对象
            UserDao userDao = new UserDaoImpl(connection);
            UserService userService = new UserServiceImpl(userDao);

            // 创建 HardwareDao、HardwareServiceImpl、AdminService 对象
            HardwareDao hardwareDao = new HardwareDaoImpl(connection);
            HardwareService hardwareService = new HardwareServiceImpl(hardwareDao);
            AdminService adminService = new AdminServiceImpl(hardwareDao);

            // 模拟用户注册和登录
            userService.registerUser("user1", "password1");
            userService.registerUser("user2", "password2");

            User loggedUser = userService.loginUser("user1", "password1");
            System.out.println("Logged in user: " + loggedUser.getUsername());

            // 模拟管理员发布硬件信息
            adminService.publishHardware("Computer1", 999.99);
            adminService.publishHardware("Computer2", 1299.99);

            // 模拟用户选择电脑硬件和下达订单
            Hardware selectedHardware = hardwareService.getHardwareById(1);
            System.out.println("Selected hardware: " + selectedHardware.getName() + " - " + selectedHardware.getPrice());

            // 关闭数据库连接
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}