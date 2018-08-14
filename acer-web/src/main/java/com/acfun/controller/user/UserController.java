package com.acfun.controller.user;

import com.acfun.core.entity.user.SysUser;
import com.acfun.core.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/** 用户控制层
 * @author  an
 */
@Controller()
@Api(value = "用户管理--返回页面", description = "用户管理--返回页面")
public class UserController {

    @ApiOperation(value = "登陆页", notes = "登陆页")
    @GetMapping(value = "/user/login.html")
    public String loginHtml() {
        return "user/login";
    }

    @ApiOperation(value = "注册页", notes = "注册页")
    @GetMapping(value = "/user/reg.html")
    public String regHtml() {
        return "user/reg";
    }

    @ApiOperation(value = "基本设置", notes = "基本设置")
    @GetMapping(value = "/user/set.html")
    public String userSet(Map<String, Object> map) {
        SysUser user = SessionUtil.getUser();

        if(user==null){
            return "user/login";
        }
        SessionUtil.setUserMap(map);
        return "user/set";
    }


}
