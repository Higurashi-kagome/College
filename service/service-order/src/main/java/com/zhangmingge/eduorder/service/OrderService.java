package com.zhangmingge.eduorder.service;

import com.zhangmingge.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 */
public interface OrderService extends IService<Order> {

	String createOrders(String courseId, String memberIdByJwtToken);
}
