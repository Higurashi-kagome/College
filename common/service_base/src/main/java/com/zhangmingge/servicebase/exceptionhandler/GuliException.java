package com.zhangmingge.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 */
@Data // getter、setter
@AllArgsConstructor // 有参构造
@NoArgsConstructor  // 无参构造
public class GuliException extends RuntimeException {
	@ApiModelProperty(value = "状态码")
	private Integer code; // 状态码
	private String msg; // 错误信息

	@Override
	public String toString() {
		return "GuliException{" +
				"message=" + this.getMessage() +
				", code=" + code +
				'}';
	}
}
