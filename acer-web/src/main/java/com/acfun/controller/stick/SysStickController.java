package com.acfun.controller.stick;

import com.acfun.core.dto.ResponseDTO;
import com.acfun.core.entity.stick.SysStick;
import com.acfun.core.entity.user.SysUser;
import com.acfun.core.service.stick.SysStickService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 帖 前端控制器
 *
 * @author an
 * @since 2018-08-14
 */
@RestController
@Slf4j
@Api(description = "帖")
public class SysStickController {

    @Autowired
    private SysStickService sysStickService;

    @ApiOperation(value = "发帖", notes = "发帖")
    @PostMapping(value = "/jie/add")
    public ResponseDTO<String> reg(@Valid SysStick sysStick) {
        return sysStickService.add(sysStick);
    }
}
