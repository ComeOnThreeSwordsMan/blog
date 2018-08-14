package com.acfun.core.entity.user;

import com.acfun.core.base.SuperEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author an
 * @since 2018-08-09
 */
@Data
@TableName("sys_user")
public class SysUser extends SuperEntity {



    /**
     * 用户名/昵称
     */
	@ApiModelProperty("用户名/昵称")
    @NotBlank
	private String username;

    /**
     * 密码
     */
	@ApiModelProperty("密码")
	private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 城市
     */
    @ApiModelProperty("城市")
    private String city;

    /**
     * 个性签名
     */
    @ApiModelProperty("个性签名")
    private String sign;

    /**
     * 头象
     */
    @ApiModelProperty("头像")
    private String photo;
}
