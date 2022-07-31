package com.zhangmingge.eduorder.service;

import com.zhangmingge.eduorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 */
public interface PayLogService extends IService<PayLog> {

	Map createNative(String orderNo);

	Map<String, String> queryPayStatus(String orderNo);

	void updateOrdersStatus(Map<String, String> map);
}
