package com.zhangmingge.servicebase.exceptionhandler;

import com.zhangmingge.commonutils.ExceptionUtil;
import com.zhangmingge.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	/**
	 * 全局异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class) // 指定处理什么异常
	@ResponseBody // 返回数据
	public R error(Exception e){
		e.printStackTrace();
		log.error(e.getMessage());
		log.error(ExceptionUtil.getMessage(e));
		return R.error().message("执行了全局异常处理...");
	}

	/**
	 * 特定异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ArithmeticException.class)
	@ResponseBody
	public R error(ArithmeticException e){
		e.printStackTrace();
		log.error(e.getMessage());
		log.error(ExceptionUtil.getMessage(e));
		return R.error().message("执行了特定异常处理...");
	}

	/**
	 * 自定义异常处理
	 */
	@ExceptionHandler(GuliException.class)
	@ResponseBody
	public R error(GuliException e){
		e.printStackTrace();
		log.error(e.getMessage());
		log.error(ExceptionUtil.getMessage(e));
		return R.error().code(e.getCode()).message(e.getMsg());
	}
}
