package pri.computerstore.dao;

import pri.computerstore.model.Hardware;

public interface HardwareDao {
    Hardware getHardwareById(int id);

    // 可以根据需要添加其他与硬件相关的数据库操作方法
}
