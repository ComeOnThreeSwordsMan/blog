package com.acfun.controller.index;

import com.acfun.core.entity.user.SysUser;
import com.acfun.core.util.SessionUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 *  首页 控制层
 * @author  --安庆
 */
@Controller
public class IndexController {

    @GetMapping(value = "/index.html")
    public String index(Map<String, Object> map) {
        //首页信息展示首页信息展示
        SessionUtil.setUserMap(map);
        return "index";
    }
    @GetMapping(value = "/")
    public String index1(Map<String, Object> map) {
        SessionUtil.setUserMap(map);
        //首页信息展示首页信息展示
        return "index";
    }





}
