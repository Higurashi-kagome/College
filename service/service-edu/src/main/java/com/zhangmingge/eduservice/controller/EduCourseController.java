package com.zhangmingge.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangmingge.commonutils.R;
import com.zhangmingge.eduservice.entity.EduCourse;
import com.zhangmingge.eduservice.entity.EduCourse;
import com.zhangmingge.eduservice.entity.vo.CourseInfoVo;
import com.zhangmingge.eduservice.entity.vo.CoursePublishVo;
import com.zhangmingge.eduservice.entity.vo.CourseQuery;
import com.zhangmingge.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/eduservice/course")
// @CrossOrigin
public class EduCourseController {
	@Autowired
	private EduCourseService courseService;

	// 添加课程基本信息的方法
	@PostMapping("addCourseInfo")
	public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
		// 返回添加之后课程id，为了后面添加大纲使用
		String id = courseService.saveCourseInfo(courseInfoVo);
		return R.ok().data("courseId", id);
	}

	//根据课程id查询课程基本信息
	@GetMapping("getCourseInfo/{courseId}")
	public R getCourseInfo(@PathVariable String courseId) {
		CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
		return R.ok().data("courseInfoVo", courseInfoVo);
	}

	//修改课程信息
	@PostMapping("updateCourseInfo")
	public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
		courseService.updateCourseInfo(courseInfoVo);
		return R.ok();
	}

	//根据课程id查询课程确认信息
	@GetMapping("getPublishCourseInfo/{id}")
	public R getPublishCourseInfo(@PathVariable String id) {
		CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
		return R.ok().data("publishCourse", coursePublishVo);
	}

	//课程最终发布
	//修改课程状态
	@PostMapping("publishCourse/{id}")
	public R publishCourse(@PathVariable String id) {
		EduCourse eduCourse = new EduCourse();
		eduCourse.setId(id);
		eduCourse.setStatus("Normal");//设置课程发布状态
		courseService.updateById(eduCourse);
		return R.ok();
	}

	//课程列表 基本实现
	@GetMapping
	public R getCourseList() {
		List<EduCourse> list = courseService.list(null);
		return R.ok().data("list", list);
	}

	//删除课程
	@DeleteMapping("{courseId}")
	public R deleteCourse(@PathVariable String courseId) {
		courseService.removeCourse(courseId);
		return R.ok();
	}

	/**
	 * 课程分页
	 */
	@ApiOperation(value = "分页课程列表")
	@GetMapping("pageCourse/{current}/{limit}")
	public R pageCourse(
			@ApiParam(name = "current", value = "当前页码", required = true)
			@PathVariable Long current,
			@ApiParam(name = "limit", value = "每页记录数", required = true)
			@PathVariable Long limit){

		// 创建 page 对象
		Page<EduCourse> pageParam = new Page<>(current, limit);
		// 调用方法实现分页
		// 调用方法时，底层将分页数据封装到 pageParam
		courseService.page(pageParam, null);
		// 获取记录
		List<EduCourse> records = pageParam.getRecords();
		// 获取总记录数
		long total = pageParam.getTotal();
		// 返回结果
		return  R.ok().data("total", total).data("rows", records);
	}

	/**
	 * 多条件分页
	 */
	@ApiOperation(value = "分页讲师列表")
	@PostMapping("pageCourseCondition/{page}/{limit}")
	public R pageCourseCondition(
			@ApiParam(name = "page", value = "当前页码", required = true)
			@PathVariable Long page,
			@ApiParam(name = "limit", value = "每页记录数", required = true)
			@PathVariable Long limit,
			@ApiParam(name = "courseQuery", value = "查询对象", required = false)
			@RequestBody(required = false) CourseQuery courseQuery){

		// 创建 page 对象
		Page<EduCourse> pageParam = new Page<>(page, limit);

		// 调用方法实现条件查询分页
		courseService.pageCourseCondition(pageParam, courseQuery);

		List<EduCourse> records = pageParam.getRecords();
		long total = pageParam.getTotal();

		return  R.ok().data("total", total).data("rows", records);
	}
}

