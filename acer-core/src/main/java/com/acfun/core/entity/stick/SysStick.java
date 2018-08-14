package com.acfun.core.entity.stick;

import com.acfun.core.base.SuperEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 帖
 * </p>
 *
 * @author an
 * @since 2018-08-14
 */
@Data
@TableName("sys_stick")
public class SysStick extends SuperEntity {

    /**
     * 用户id
     */
	@ApiModelProperty("用户id")
	private Long userId;

	@ApiModelProperty("发帖人")
	private String userName;


    /**
     * 所在专栏
     */
	@ApiModelProperty("所在专栏")
	@NotBlank
	private String  fold;

    /**
     * 标题
     */
	@ApiModelProperty("标题")
	@NotBlank
	private String title;

    /**
     * 详细描述
     */
	@ApiModelProperty("详细描述")
	private String content;

    /**
     * 悬赏评分
     */
	@ApiModelProperty("悬赏评分")
	private Integer experience;

	/**
	 * 所属产品
	 */
	@ApiModelProperty("所属产品")
	private String project;

	/**
	 * 版本号
	 */
	@ApiModelProperty("版本号")
	private String version;
}
