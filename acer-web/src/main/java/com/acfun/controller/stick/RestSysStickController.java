package com.acfun.controller.stick;

import com.acfun.common.util.FileUploadUtils;
import com.acfun.controller.user.UserRestController;
import com.acfun.core.dto.ResponseDTO;
import com.acfun.core.entity.stick.SysStick;
import com.acfun.core.enums.ResponseCode;
import com.acfun.core.service.stick.SysStickService;
import com.acfun.core.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.UUID;

/**
 * 帖 前端控制器
 *
 * @author an
 * @since 2018-08-14
 */
@RestController
@Slf4j
@Api(description = "帖")
public class RestSysStickController {

    @Autowired
    private SysStickService sysStickService;

    @ApiOperation(value = "发帖", notes = "发帖")
    @PostMapping(value = "/jie/add")
    public ResponseDTO<String> add(@Valid SysStick sysStick) {
        return sysStickService.add(sysStick);
    }

    @ApiOperation(value = "发帖", notes = "发帖")
    @PostMapping(value = "/jie/upload")
    public ResponseDTO<String> upload(@RequestParam(name="file") MultipartFile file) {
        try {
            String path = UserRestController.class.getResource("/").toURI().getPath();
            String name =   UUID.randomUUID().toString() + FileUploadUtils.getEndName(file.getOriginalFilename());
            FileUploadUtils.uploadFile(file.getBytes(), path + "/static/upload/jie/" + SessionUtil.getUserId() +"/" , name);
            name = "/upload/jie/" + SessionUtil.getUserId() +"/"  + name;
            return new ResponseDTO<>(ResponseCode.OK,name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseDTO<>(ResponseCode.UNLOAD_SUCCESS);
    }

}
