// UserService.java
package pri.computerstore.service;

import pri.computerstore.model.User;

/**
 * @author 33133
 */
public interface UserService {
    User registerUser(String username, String password);

    User loginUser(String username, String password);

    // 可以根据需要添加其他与用户相关的服务方法
}
