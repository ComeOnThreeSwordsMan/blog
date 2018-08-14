package com.acfun.controller.user;

import com.acfun.common.util.FileUploadUtils;
import com.acfun.core.dto.ResponseDTO;
import com.acfun.core.entity.user.SysUser;
import com.acfun.core.enums.ResponseCode;
import com.acfun.core.service.user.SysUserService;
import com.acfun.core.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

/** 用户控制层
 * @author  an
 */
@RestController()
@Api(value = "用户管理--返回json", description = "用户管理--返回json")
public class UserRestController {

    @Autowired
    private SysUserService sysUserService;
    @Value("${path}")
    private String path;

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

    @ApiOperation(value = "用户修改", notes = "用户修改")
    @PostMapping(value = "/user/set")
    public   ResponseDTO<String>  set(@Valid SysUser sysUser) {
        return sysUserService.set(sysUser);
    }

    @ApiOperation(value = "基本设置", notes = "基本设置")
    @PostMapping(value = "/user/upload/")
    public ResponseDTO<String> upload(@RequestParam(name="file") MultipartFile file) {
        SysUser user = SessionUtil.getUser();

        if(user==null){
            return new ResponseDTO<>(ResponseCode.DO_LOGIN);
        }
        try {
            String path = UserRestController.class.getResource("/").toURI().getPath();
            FileUploadUtils.uploadFile(file.getBytes(), path + "/static/upload/headImg/" , user.getId() + FileUploadUtils.getEndName(file.getOriginalFilename()));
            user.setPhoto("/upload/headImg/"+user.getId() + FileUploadUtils.getEndName(file.getOriginalFilename()));
            user.updateById();
            SessionUtil.setUser(user);
            return new ResponseDTO<>(ResponseCode.UNLOAD_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseDTO<>(ResponseCode.DO_LOGIN);
    }
}
