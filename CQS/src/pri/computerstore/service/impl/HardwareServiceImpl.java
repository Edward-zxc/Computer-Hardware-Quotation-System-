// HardwareServiceImpl.java
package pri.computerstore.service.impl;

import pri.computerstore.dao.HardwareDao;
import pri.computerstore.model.Hardware;
import pri.computerstore.service.HardwareService;

/**
 * @author 33133
 */
public class HardwareServiceImpl implements HardwareService {
    private final HardwareDao hardwareDao;

    public HardwareServiceImpl(HardwareDao hardwareDao) {
        this.hardwareDao = hardwareDao;
    }

    @Override
    public Hardware getHardwareById(int id) {
        return hardwareDao.getHardwareById(id);
    }

    // 可以根据需要添加其他与硬件相关的服务方法的实现
}
