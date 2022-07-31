package com.zhangmingge.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangmingge.commonutils.R;
import com.zhangmingge.eduservice.client.VodClient;
import com.zhangmingge.eduservice.entity.EduVideo;
import com.zhangmingge.eduservice.entity.vo.VideoInfoVo;
import com.zhangmingge.eduservice.mapper.EduVideoMapper;
import com.zhangmingge.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangmingge.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

	@Autowired
	private VodClient vodClient;

	@Override
	public VideoInfoVo getVideoInfo(String videoId)
	{
		VideoInfoVo videoInfoVo = new VideoInfoVo();
		EduVideo video = baseMapper.selectById(videoId);
		BeanUtils.copyProperties(video, videoInfoVo);
		return videoInfoVo;
	}

	//1 根据课程id删除小节（及视频）
	@Override
	public void removeVideoByCourseId(String courseId) {
		/* 删除视频 */
		//1 根据课程id查询课程所有的视频id
		QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
		wrapperVideo.eq("course_id", courseId);
		wrapperVideo.select("video_source_id");
		List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideo);

		// List<EduVideo>变成List<String>
		List<String> videoIds = new ArrayList<>();
		for (int i = 0; i < eduVideoList.size(); i++) {
			EduVideo eduVideo = eduVideoList.get(i);
			String videoSourceId = eduVideo.getVideoSourceId();
			if(!StringUtils.isEmpty(videoSourceId)) {
				//放到videoIds集合里面
				videoIds.add(videoSourceId);
			}
		}

		//根据多个视频id删除多个视频
		if(videoIds.size() > 0) {
			vodClient.deleteBatch(videoIds);
		}

		/* 删除小节 */
		QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
		wrapper.eq("course_id",courseId);
		baseMapper.delete(wrapper);
	}

	@Override
	public void removeVideoMediaByVideoId(String videoId)
	{
		//根据小节id获取视频id，调用方法实现视频删除
		EduVideo eduVideo = this.getById(videoId);
		String videoSourceId = eduVideo.getVideoSourceId();
		System.out.println(videoId);
		System.out.println(videoSourceId);
		//判断小节里面是否有视频id
		if(!StringUtils.isEmpty(videoSourceId)) {
			//根据视频id，远程调用实现视频删除
			R result = vodClient.removeAlyVideo(videoSourceId);
			if(result.getCode() == 20001) {
				throw new GuliException(20001, "删除视频失败，熔断器...");
			}
		}
	}
}
