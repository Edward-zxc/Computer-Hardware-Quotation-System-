package pri.computerstore.service.impl;

import pri.computerstore.dao.HardwareDao;
import pri.computerstore.model.Hardware;
import pri.computerstore.service.AdminService;

/**
 * @author 33133
 */
public class AdminServiceImpl implements AdminService {
    private final HardwareDao hardwareDao;

    public AdminServiceImpl(HardwareDao hardwareDao) {
        this.hardwareDao = hardwareDao;
    }

    @Override
    public void publishHardware(String name, double price) {
        // 实现管理员发布电脑硬件信息的业务逻辑
        Hardware newHardware = new Hardware(name, price);
        // 可以添加其他处理，如数据验证等
        hardwareDao.saveHardware(newHardware);
    }

    // 可以根据需要添加其他与管理员相关的服务方法的实现
}
