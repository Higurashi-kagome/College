package com.zhangmingge.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VideoInfoVo {
	@ApiModelProperty(value = "小节ID")
	private String id;

	@ApiModelProperty(value = "课程ID")
	private String courseId;

	@ApiModelProperty(value = "章节ID")
	private String chapterId;

	@ApiModelProperty(value = "小节标题")
	private String title;

	@ApiModelProperty(value = "小节排序")
	private Integer sort;

	@ApiModelProperty(value = "是否免费")
	private Integer isFree;

	@ApiModelProperty(value = "视频ID")
	private String videoSourceId;

	// @ApiModelProperty(value = "视频名称")
	// private String videoOriginalName;
	//
	// @ApiModelProperty(value = "视频播放量")
	// private Integer playCount;
	//
	// @ApiModelProperty(value = "视频时长")
	// private Long duration;
}
