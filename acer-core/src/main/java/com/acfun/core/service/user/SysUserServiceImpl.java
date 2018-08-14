package com.acfun.core.service.user;

import com.acfun.common.constant.SessionKey;
import com.acfun.core.dto.ResponseDTO;
import com.acfun.core.entity.user.SysUser;
import com.acfun.core.enums.ResponseCode;
import com.acfun.core.mapper.user.SysUserRepository;
import com.acfun.core.util.SessionUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * 用户表 服务实现类
 *
 * @author an
 * @since 2018-08-09
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserRepository, SysUser> implements SysUserService {

    /**
     * 新增用户
     *
     * @param sysUser 用户信息
     * @return 添加结果
     */
    @Override
    public ResponseDTO<String> add(SysUser sysUser) {
        SysUser user = selectOne(new EntityWrapper<SysUser>().eq("username", sysUser.getUsername()));
        if(user!=null){
            return new ResponseDTO<>(ResponseCode.USER_NAME_EXIST);
        }
        user =selectOne(new EntityWrapper<SysUser>().eq("email", sysUser.getEmail()));
        if(user!=null){
            return new ResponseDTO<>(ResponseCode.EMAIL_EXIST);
        }
        //加密
        sysUser.setPassword(DigestUtils.md5Hex(sysUser.getPassword()));
        sysUser.insert();
        return new ResponseDTO<>(ResponseCode.REG_SUCCESS);
    }

    /**
     * 用户登陆
     *
     * @param sysUser 用户登陆
     * @return 用户登陆
     */
    @Override
    public ResponseDTO<String> login(SysUser sysUser) {
        SysUser user = selectOne(new EntityWrapper<SysUser>().eq("username", sysUser.getUsername()));
        if(user==null){
            return new ResponseDTO<>(ResponseCode.USER_NAME_NOT_EXIST);
        }
        if(!user.getPassword().equals(DigestUtils.md5Hex(sysUser.getPassword()))){
            return new ResponseDTO<>(ResponseCode.WRONG_OLD_PASSWORD);
        }
        HttpSession session = SessionUtil.getSession();
        session.setAttribute("user",user);
        return new ResponseDTO<>(ResponseCode.LOGIN_SUCCESS);
    }

    /**
     * 修改用户
     *
     * @param sysUser 用户
     * @return 修改用户
     */
    @Override
    public ResponseDTO<String> set(SysUser sysUser) {
        SysUser user = selectById(sysUser);
        if(user!=null){
            updateById(sysUser);
            SessionUtil.getSession().setAttribute(SessionKey.USER,sysUser);
            return new ResponseDTO<>(ResponseCode.SET_SUCCESS);
        }
        return new ResponseDTO<>(ResponseCode.LOGIN_SUCCESS);
    }
}
