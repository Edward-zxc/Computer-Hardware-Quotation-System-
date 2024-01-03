package pri.computerstore.dao;

import pri.computerstore.model.Hardware;

/**
 * @author 33133
 */
public interface HardwareDao {
    Hardware getHardwareById(int id);

    void saveHardware(Hardware newHardware);

    // 可以根据需要添加其他与硬件相关的数据库操作方法
}
