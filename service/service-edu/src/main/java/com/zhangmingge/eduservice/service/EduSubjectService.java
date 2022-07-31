package com.zhangmingge.eduservice.service;

import com.zhangmingge.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangmingge.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 */
public interface EduSubjectService extends IService<EduSubject> {

	/**
	 * 添加课程分类
	 * @param file
	 * @param subjectService
	 */
	void saveSubject(MultipartFile file, EduSubjectService subjectService);

	/**
	 * 获取课程分类列表（树形）数据
	 * @return
	 */
	List<OneSubject> getAllOneTwoSubject();
}
