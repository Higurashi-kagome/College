package com.zhangmingge.eduservice.controller;


import com.zhangmingge.commonutils.R;
import com.zhangmingge.eduservice.entity.subject.OneSubject;
import com.zhangmingge.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/eduservice/subject")
// @CrossOrigin
public class EduSubjectController {
	@Autowired
	private EduSubjectService subjectService;

	/**
	 * 添加课程分类<br>
	 * 获取上传过来文件，把文件内容读取出来
	 * @param file
	 * @return
	 */
	@PostMapping("addSubject")
	public R addSubject(MultipartFile file) {
		// 处理上传过来的 excel 文件
		subjectService.saveSubject(file, subjectService);
		return R.ok();
	}


	/**
	 * 获取课程分类列表（树形）数据
	 * @return
	 */
	@GetMapping("getAllSubject")
	public R getAllSubject() {
		// list 集合泛型是一级分类
		List<OneSubject> list = subjectService.getAllOneTwoSubject();
		return R.ok().data("list", list);
	}
}

