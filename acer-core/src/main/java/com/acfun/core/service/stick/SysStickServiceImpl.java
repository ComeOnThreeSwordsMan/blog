package com.acfun.core.service.stick;

import com.acfun.core.dto.ResponseDTO;
import com.acfun.core.entity.stick.SysStick;
import com.acfun.core.enums.ResponseCode;
import com.acfun.core.mapper.stick.SysStickRepository;
import com.acfun.core.util.SessionUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 帖 服务实现类
 *
 * @author an
 * @since 2018-08-14
 */
@Service
public class SysStickServiceImpl extends ServiceImpl<SysStickRepository, SysStick> implements SysStickService {

    /**
     * 发帖
     *
     * @param sysStick 发帖
     * @return 发帖
     */
    @Override
    public ResponseDTO<String> add(SysStick sysStick) {
        sysStick.setUserName(SessionUtil.getUserName());
        sysStick.setUserId(SessionUtil.getUserId());
        sysStick.insert();
        return new ResponseDTO<>(ResponseCode.OK);
    }
}
