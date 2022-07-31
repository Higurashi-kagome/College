package com.zhangmingge.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangmingge.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangmingge.eduservice.entity.frontvo.CourseFrontVo;
import com.zhangmingge.eduservice.entity.frontvo.CourseWebVo;
import com.zhangmingge.eduservice.entity.vo.CourseInfoVo;
import com.zhangmingge.eduservice.entity.vo.CoursePublishVo;
import com.zhangmingge.eduservice.entity.vo.CourseQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 */
public interface EduCourseService extends IService<EduCourse> {
	/**
	 * 添加课程基本信息的方法
	 * @param courseInfoVo
	 * @return
	 */
	String saveCourseInfo(CourseInfoVo courseInfoVo);

	CourseInfoVo getCourseInfo(String courseId);

	void updateCourseInfo(CourseInfoVo courseInfoVo);

	CoursePublishVo publishCourseInfo(String id);

	void removeCourse(String courseId);

	void pageCourseCondition(Page<EduCourse> pageParam, CourseQuery courseQuery);

	//查询前8条热门课程
	List<EduCourse> getHotEight();

	Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

	CourseWebVo getBaseCourseInfo(String courseId);
}
