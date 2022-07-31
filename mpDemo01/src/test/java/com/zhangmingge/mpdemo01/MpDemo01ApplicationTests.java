package com.zhangmingge.mpdemo01;

import com.zhangmingge.mpdemo01.entity.User;
import com.zhangmingge.mpdemo01.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MpDemo01ApplicationTests {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 查询 user 表所有数据
	 */
	@Test
	public void findAll() {
		List<User> users = userMapper.selectList(null);
		System.out.println(users);
	}
}
