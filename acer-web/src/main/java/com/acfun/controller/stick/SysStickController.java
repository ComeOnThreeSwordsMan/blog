package com.acfun.controller.stick;

import com.acfun.core.entity.stick.SysStick;
import com.acfun.core.entity.user.SysUser;
import com.acfun.core.service.stick.SysStickService;
import com.acfun.core.util.SessionUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * 帖 前端控制器
 *
 * @author an
 * @since 2018-08-14
 */
@Controller
@Slf4j
@Api(description = "帖")
public class SysStickController {

    @Autowired
    private SysStickService sysStickService;

    @ApiOperation(value = "帖-列表", notes = "帖-列表")
    @GetMapping(value = "/user/index.html")
    public String add( Page<SysStick> page,Map<String, Object> map) {
        SysUser user = SessionUtil.getUser();
        if(user==null){
            return "user/login";
        }
        EntityWrapper<SysStick> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id",SessionUtil.getUserId());
        Page<SysStick> sysStickPage = sysStickService.selectPage(page,wrapper);
        map.put("list",sysStickPage.getRecords());
       // map.put("page",sysStickPage);
       // SessionUtil.setUserMap(map);
        return "user/index";
    }


    @ApiOperation(value = "帖-详情", notes = "帖-详情")
    @GetMapping(value = "/jie/detail.html")
    public String detail( Long id,Map<String, Object> map) {
        SysUser user = SessionUtil.getUser();
        if(user==null){
            return "user/login";
        }
        map.put("stick", sysStickService.selectById(id));
        return "jie/detail";
    }


}
