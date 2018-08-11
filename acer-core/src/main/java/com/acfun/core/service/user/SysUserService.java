package com.acfun.core.service.user;

import com.acfun.core.dto.ResponseDTO;
import com.acfun.core.entity.user.SysUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * 用户表 服务类
 *
 * @author an
 * @since 2018-08-09
 */
public interface SysUserService extends IService<SysUser> {
    /**
     *  新增用户
     * @param sysUser 用户信息
     * @return 添加结果
     */
    ResponseDTO<String> add(SysUser sysUser);

    /**
     *  用户登陆
     * @param sysUser 用户登陆
     * @return 用户登陆
     */
    ResponseDTO<String>  login(SysUser sysUser);
}
