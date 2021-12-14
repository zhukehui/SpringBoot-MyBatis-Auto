package com.fhd.springboot.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础Entity类
 *
 * @author fhd
 * @date 2020年10月9日
 */
@ApiModel("基础Entity类")
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -9166207060433517368L;

	@ApiModelProperty("创建者")
	protected String createBy; // 创建者
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "创建日期", required = true, example = "yyyy-MM-dd HH:mm:ss")
	protected Date createDate; // 创建日期	
	@ApiModelProperty("更新者")
	protected String updateBy; // 更新者
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "更新日期", required = true, example = "yyyy-MM-dd HH:mm:ss")
	protected Date updateDate; // 更新日期

	@ApiModelProperty("删除标记（0：正常,1：删除）")
	protected int delFlag; // 删除标记（0：正常,1：删除）

	@ApiModelProperty("页码")
	protected int pageNum; // 当前页码
	@ApiModelProperty("页码数量")
	protected int pageSize;
	@ApiModelProperty("是否查询总条数")
	@JsonInclude(Include.NON_NULL)
	protected Boolean count;
	@ApiModelProperty("排序")
	@JsonInclude(Include.NON_NULL)
	protected String orderBy;

	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "开始时间", required = true, example = "yyyy-MM-dd HH:mm:ss")
	private Date beginTime;
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "结束时间", required = true, example = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public int getPageNum() {
		return this.pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Boolean getCount() {
		return this.count;
	}

	public void setCount(Boolean count) {
		this.count = count;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "BaseEntity [createBy=" + this.createBy + ", createDate=" + this.createDate + ", updateBy=" + this.updateBy + ", updateDate="
				+ this.updateDate + ", delFlag=" + this.delFlag + ", pageNum=" + this.pageNum + ", pageSize=" + this.pageSize + ", count="
				+ this.count + ", orderBy=" + this.orderBy + ", beginTime=" + this.beginTime + ", endTime=" + this.endTime + "]";
	}

}
