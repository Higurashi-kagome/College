package com.zhangmingge.eduservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangmingge.commonutils.R;
import com.zhangmingge.eduservice.entity.EduTeacher;
import com.zhangmingge.eduservice.entity.vo.TeacherQuery;
import com.zhangmingge.eduservice.service.EduTeacherService;
import com.zhangmingge.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 */
@Api(description="讲师管理")
@RestController // @ResponseBody 和 @Controller 的组合注解
// @CrossOrigin  //解决跨域
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
	@Autowired
	private EduTeacherService teacherService;

	/**
	 * 查找讲师列表
	 * @return
	 */
	@ApiOperation(value = "所有讲师列表")
	@GetMapping("findAll")
	public R list(){
		// 测试统一异常处理
		/*try {
			int i = 1/0;
		} catch (Exception e){
			// throw e;
			// throw new ArithmeticException();
			throw new GuliException(20001, "执行了自定义异常处理...");
		}*/
		List<EduTeacher> list = teacherService.list(null);
		return R.ok().data("items", list);
	}

	/**
	 * 逻辑删除讲师
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "逻辑删除讲师")
	@DeleteMapping("{id}")
	public R removeById(
			@ApiParam(name = "id", value = "讲师ID", required = true)
			@PathVariable String id){
		boolean b = teacherService.removeById(id);
		if (b == true){
			return R.ok();
		} else {
			return R.error();
		}
	}

	/**
	 * 讲师分页
	 * @param current
	 * @param limit
	 * @return
	 */
	@ApiOperation(value = "分页讲师列表")
	@GetMapping("pageTeacher/{current}/{limit}")
	public R pageTeacher(
			@ApiParam(name = "current", value = "当前页码", required = true)
			@PathVariable Long current,

			@ApiParam(name = "limit", value = "每页记录数", required = true)
			@PathVariable Long limit){

		// 创建 page 对象
		Page<EduTeacher> pageParam = new Page<>(current, limit);
		// 调用方法实现分页
		// 调用方法时，底层将分页数据封装到 pageParam
		teacherService.page(pageParam, null);
		// 获取记录
		List<EduTeacher> records = pageParam.getRecords();
		// 获取总记录数
		long total = pageParam.getTotal();
		// 返回结果
		return  R.ok().data("total", total).data("rows", records);
	}

	/**
	 * 多条件分页
	 * @param page
	 * @param limit
	 * @param teacherQuery
	 * @return
	 */
	@ApiOperation(value = "分页讲师列表")
	@PostMapping("pageTeacherCondition/{page}/{limit}")
	public R pageTeacherCondition(
			@ApiParam(name = "page", value = "当前页码", required = true)
			@PathVariable Long page,

			@ApiParam(name = "limit", value = "每页记录数", required = true)
			@PathVariable Long limit,

			@ApiParam(name = "teacherQuery", value = "查询对象", required = false)
			@RequestBody(required = false) TeacherQuery teacherQuery){

		// 创建 page 对象
		Page<EduTeacher> pageParam = new Page<>(page, limit);

		// 调用方法实现条件查询分页
		teacherService.pageTeacherCondition(pageParam, teacherQuery);

		List<EduTeacher> records = pageParam.getRecords();
		long total = pageParam.getTotal();

		return  R.ok().data("total", total).data("rows", records);
	}

	/**
	 * 添加讲师
	 * @param teacher
	 * @return
	 */
	@ApiOperation(value = "新增讲师")
	@PostMapping("addTeacher")
	public R save(
			@ApiParam(name = "teacher", value = "讲师对象", required = true)
			@RequestBody EduTeacher teacher){
		boolean save = teacherService.save(teacher);
		if (save) {
			return R.ok();
		} else {
			return R.error();
		}
	}

	@ApiOperation(value = "根据ID查询讲师")
	@GetMapping("getTeacher/{id}")
	public R getById(
			@ApiParam(name = "id", value = "讲师ID", required = true)
			@PathVariable String id){
		EduTeacher teacher = teacherService.getById(id);
		return R.ok().data("teacher", teacher);
	}

	@ApiOperation(value = "根据ID修改讲师")
	@PostMapping("updateTeacher")
	public R updateById(
			@ApiParam(name = "teacher", value = "讲师对象", required = true)
			@RequestBody EduTeacher teacher){
		boolean flag = teacherService.updateById(teacher);
		if(flag){
			return R.ok();
		} else {
			return R.error();
		}
	}
}

