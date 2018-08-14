package com.acfun.core.service.stick;

import com.acfun.core.dto.ResponseDTO;
import com.acfun.core.entity.stick.SysStick;
import com.baomidou.mybatisplus.service.IService;

/**
 * 帖 服务类
 *
 * @author an
 * @since 2018-08-14
 */
public interface SysStickService extends IService<SysStick> {
    /**
     * 发帖
     * @param sysStick 发帖
     * @return 发帖
     */
    ResponseDTO<String> add(SysStick sysStick);
}
