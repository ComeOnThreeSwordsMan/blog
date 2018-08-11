/*
 * Copyright (c) 2017. www.servingcloud.com Inc. All rights reserved.
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.acfun.core.dto;

import com.acfun.core.enums.ResponseCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @author hsky www.servingcloud.com
 * @since 2017年6月7日 上午10:53:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("响应实体")
public class ResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  返回代码
     */
    @ApiModelProperty("编码 200:成功,其他异常")
    private int status;

    @ApiModelProperty("编码 200:成功,其他异常")
    private int code;

    /**
     * 返回消息提示
     */
    @ApiModelProperty("消息")
    private String msg;

    /**
     * 返回内容
     */
    @ApiModelProperty("数据结果集")
    private T data;

    public ResponseDTO(ResponseCode responseCode) {
        this.status = responseCode.status();
        this.msg = responseCode.getDescription();
        this.code = responseCode.code();
    }

    public ResponseDTO(ResponseCode responseCode, T data) {
        this.status = responseCode.status();
        this.msg = responseCode.getDescription();
        this.code = responseCode.code();
        this.data = data;
    }

}
