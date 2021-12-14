package com.fhd.springboot.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * api通用返回数据
 *
 * @author fhd
 * @date 2020年10月9日
 */
@ApiModel("api通用返回数据")
public class RestResult implements Serializable {

    private static final long serialVersionUID = 676073612839477184L;

    /**
     * 标识代码，200表示成功，非200表示出错
     */
    @ApiModelProperty("标识代码,200表示成功，非200表示出错")
    @JsonInclude(Include.NON_NULL) // 将该标记放在属性上，如果该属性为NULL则不参与json化
    private Integer code;

    /**
     * 提示信息，通常供报错时使用
     */
    @ApiModelProperty("提示信息,供报错时使用")
    @JsonInclude(Include.NON_NULL) // 将该标记放在属性上，如果该属性为NULL则不参与json化
    private String msg;

    /**
     * 正常返回时返回的数据
     */
    @ApiModelProperty("返回的数据")
//	    @JsonInclude(Include.NON_NULL)//将该标记放在属性上，如果该属性为NULL则不参与json化
    private Object data;

    /**
     * 分页 总数
     */
    @ApiModelProperty("分页 总数")
    @JsonInclude(Include.NON_NULL)
    private Long count;

    public RestResult() {
    }

    public RestResult(Integer status, String msg, Object data, Long count) {
        this.code = status;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    /**
     * 返回成功信息
     */
    public static RestResult success() {
        return new RestResult(200, "SUCCESS", null, null);
    }

    /**
     * 返回成功信息
     *
     * @param data 返回数据
     */
    public static RestResult success(Object data) {
        return new RestResult(200, "SUCCESS", data, null);
    }

    /**
     * 返回成功信息
     *
     * @param data  返回数据
     * @param count 数据总数
     */
    public static RestResult success(Object data, Long count) {
        return new RestResult(200, "SUCCESS", data, count);
    }

    /**
     * 返回成功信息
     *
     * @param msg 提示信息
     */
    public static RestResult success(String msg) {
        return new RestResult(200, msg, null, null);
    }

    /**
     * 返回失败信息
     *
     * @param code 返回code
     * @param msg  提示信息
     */
    public static RestResult error(Integer code, String msg) {
        return new RestResult(code, msg, null, null);
    }

    /**
     * 返回失败信息
     *
     * @param msg 提示信息
     */
    public static RestResult error(String msg) {
        return new RestResult(1000, msg, null, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}