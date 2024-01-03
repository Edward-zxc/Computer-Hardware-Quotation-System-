// UserServiceImpl.java
package pri.computerstore.service.impl;

import pri.computerstore.dao.UserDao;
import pri.computerstore.model.User;
import pri.computerstore.service.UserService;

/**
 * @author 33133
 */
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User registerUser(String username, String password) {
        User newUser = new User();
        return userDao.registerUser(newUser);
    }

    @Override
    public User loginUser(String username, String password) {
        return userDao.loginUser(username, password);
    }

    // 可以根据需要添加其他与用户相关的服务方法的实现
}
