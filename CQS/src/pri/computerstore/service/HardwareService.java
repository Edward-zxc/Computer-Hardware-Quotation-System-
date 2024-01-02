package pri.computerstore.service;

import pri.computerstore.model.Hardware;

/**
 * @author 33133
 */
public interface HardwareService {
    Hardware getHardwareById(int id);

    // 可以根据需要添加其他与硬件相关的服务方法
}
