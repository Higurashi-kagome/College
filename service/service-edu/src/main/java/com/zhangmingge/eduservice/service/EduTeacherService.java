package com.zhangmingge.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangmingge.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangmingge.eduservice.entity.vo.TeacherQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 */
public interface EduTeacherService extends IService<EduTeacher> {
	void pageTeacherCondition(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

	//查询前4条名师
	List<EduTeacher> getFirstFour();

	Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
