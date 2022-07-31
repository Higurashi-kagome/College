package com.zhangmingge.eduucenter.service;

import com.zhangmingge.eduucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangmingge.eduucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 */
public interface UcenterMemberService extends IService<UcenterMember> {

	String login(UcenterMember member);

	void register(RegisterVo registerVo);

	// 根据 openid 查询用户
	UcenterMember getOpenIdMember(String openid);

	Integer countRegisterDay(String day);
}
