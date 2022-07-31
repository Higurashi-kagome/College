package com.zhangmingge.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "Courser查询对象", description = "课程查询对象封装")
@Data
public class CourseQuery implements Serializable {
	private static final long serialVersionUID = 2L;

	@ApiModelProperty(value = "课程名称,模糊查询")
	private String title;

	@ApiModelProperty(value = "课程状态 Draft Normal")
	private String status;
}
