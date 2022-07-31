package com.zhangmingge.eduucenter.mapper;

import com.zhangmingge.eduucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
	Integer countRegisterDay(String day);
}
