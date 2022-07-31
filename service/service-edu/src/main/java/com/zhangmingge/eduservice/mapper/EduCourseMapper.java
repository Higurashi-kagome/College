package com.zhangmingge.eduservice.mapper;

import com.zhangmingge.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangmingge.eduservice.entity.frontvo.CourseWebVo;
import com.zhangmingge.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
	CoursePublishVo getPublishCourseInfo(String id);
	//根据课程 id 查询课程的基本信息
	CourseWebVo getBaseCourseInfo(String courseId);
}
