package com.acfun.controller.user;

import com.acfun.core.dto.ResponseDTO;
import com.acfun.core.entity.user.SysUser;
import com.acfun.core.service.user.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/** 用户控制层
 * @author  an
 */
@RestController()
@Api(value = "用户管理--返回json", description = "用户管理--返回json")
public class UserRestController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping(value = "/user/reg")
    public ResponseDTO<String> reg(@Valid SysUser sysUser) {
        return sysUserService.add(sysUser);
    }


    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @PostMapping(value = "/user/login")
    public   ResponseDTO<String>  login(@Valid SysUser sysUser) {
        return sysUserService.login(sysUser);
    }



}
