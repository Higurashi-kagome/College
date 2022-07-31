package com.zhangmingge.eduservice.service;

import com.zhangmingge.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangmingge.eduservice.entity.vo.VideoInfoVo;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 */
public interface EduVideoService extends IService<EduVideo> {
	VideoInfoVo getVideoInfo(String videoId);

	void removeVideoByCourseId(String courseId);

	void removeVideoMediaByVideoId(String courseId);
}
