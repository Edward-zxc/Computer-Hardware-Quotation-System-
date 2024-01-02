package pri.computerstore.dao;

import pri.computerstore.model.User;

/**
 * @author 33133
 */
public interface UserDao {
    User registerUser(User user);

    User loginUser(String username, String password);

    // 可以根据需要添加其他与用户相关的数据库操作方法
}