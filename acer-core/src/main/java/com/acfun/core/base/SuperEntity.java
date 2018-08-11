package com.acfun.core.base;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * description mybatis-plus自动补充基类
 *
 * @author 安
 */
@Data
public class SuperEntity<T extends Model> extends Model<T> {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 创建时间
     */
    @TableField(value = "created_date", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间",hidden = true)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    /**
     * 修改时间
     */
    @TableField(value = "last_modified_date", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDate;

    /**
     *  创建人userid
     */
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人",hidden = true)
    private String createdBy;

    /**
     * 修改人userid
     */
    @TableField(value = "last_modified_by", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改人")
    private String lastModifiedBy;

    /**
     * 版本号
     */
//    version用于高并发场景,若需要使用请在pojo中声明使用即可
//    @ApiModelProperty(value = "版本号",hidden = true,required = true)
//    @Version
//    private int version = 1;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
