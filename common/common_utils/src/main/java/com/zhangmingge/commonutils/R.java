package com.zhangmingge.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一结果
 */
@Data
public class R {
	@ApiModelProperty(value = "是否成功") // swagger 注解
	private Boolean success;

	@ApiModelProperty(value = "返回码")
	private Integer code;

	@ApiModelProperty(value = "返回消息")
	private String message;

	@ApiModelProperty(value = "返回数据")
	private Map<String, Object> data = new HashMap<String, Object>();

	private R(){}

	/**
	 * 获取 ok 结果对象
	 * @return
	 */
	public static R ok(){
		R r = new R();
		r.setSuccess(true);
		r.setCode(ResultCode.SUCCESS);
		r.setMessage("成功");
		return r;
	}

	/**
	 * 获取 error 结果对象
	 * @return
	 */
	public static R error(){
		R r = new R();
		r.setSuccess(false);
		r.setCode(ResultCode.ERROR);
		r.setMessage("失败");
		return r;
	}

	/**
	 * 设置 success 属性
	 * @param success
	 * @return
	 */
	public R success(Boolean success){
		this.setSuccess(success);
		return this; // 链式编程
	}

	public R message(String message){
		this.setMessage(message);
		return this;
	}

	public R code(Integer code){
		this.setCode(code);
		return this;
	}

	/**
	 * 往 data 中添加数据
	 * @param key
	 * @param value
	 * @return
	 */
	public R data(String key, Object value){
		this.data.put(key, value);
		return this;
	}

	/**
	 * 设置 data 属性
	 * @param map
	 * @return
	 */
	public R data(Map<String, Object> map){
		this.setData(map);
		return this;
	}
}
