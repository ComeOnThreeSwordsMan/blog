package com.acfun.core.entity.stick;

import com.acfun.core.base.SuperEntity;

import com.baomidou.mybatisplus.annotations.Version;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 帖
 * </p>
 *
 * @author an
 * @since 2018-08-14
 */
@Data
public class SysStick extends SuperEntity {



    /**
     * 用户id
     */
	@ApiModelProperty("用户id")
	private Long userId;

    /**
     * 所在专栏
     */
	@ApiModelProperty("所在专栏")
	private String  fold;

    /**
     * 标题
     */
	@ApiModelProperty("标题")
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


}
