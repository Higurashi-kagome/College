package com.zhangmingge.mpdemo02;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangmingge.mpdemo02.entity.User;
import com.zhangmingge.mpdemo02.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MpDemo02ApplicationTests {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 添加操作
	 */
	@Test
	public void addUser() {
		User user = new User();
		user.setName("lucy");
		user.setAge(20);
		user.setEmail("lucy@qq.com");
		int insert = userMapper.insert(user);
		System.out.println("insert:"+insert);
	}

	/**
	 * 修改操作
	 */
	@Test
	public void updateUser() {
		User user = new User();
		user.setId(2L);
		user.setAge(120);
		// user.setUpdateTime(new Date(System.currentTimeMillis())); // 不使用自动填充则需要调用 setter 方法
		int row = userMapper.updateById(user);
		System.out.println(row);
	}

	/**
	 * 测试乐观锁
	 */
	@Test
	public void testOptimisticLocker() {
		// 根据 id 查询数据
		User user = userMapper.selectById(2L);
		// 进行修改
		user.setAge(200);
		userMapper.updateById(user);
	}

	/**
	 * 多个 id 批量查询
	 */
	@Test
	public void testSelectByIds() {
		List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
		System.out.println(users);
	}

	/**
	 * 通过 map 封装查询条件
	 */
	@Test
	public void testSelectByMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "Jone");
		map.put("age", 18);
		List<User> users = userMapper.selectByMap(map);
		users.forEach(System.out::println);
	}

	/**
	 * 分页查询
	 */
	@Test
	public void testPage() {
		// 1 创建 page 对象
		// 传入两个参数：当前页，每页显示记录数
		Page<User> page = new Page<>(1,3);
		// 调用 mp 分页查询的方法
		// 调用 mp 分页查询过程中，底层把分页所有数据封装到 page 对象里面
		userMapper.selectPage(page,null);

		// 通过page对象获取分页数据
		System.out.println(page.getCurrent()); // 当前页
		System.out.println(page.getRecords()); // 每页数据 list 集合
		System.out.println(page.getSize());    // 每页显示记录数
		System.out.println(page.getTotal());   // 总记录数
		System.out.println(page.getPages());   // 总页数

		System.out.println(page.hasNext());     // 下一页
		System.out.println(page.hasPrevious()); // 上一页
	}

	/**
	 * 根据 id 删除
	 */
	@Test
	public void testDeleteById(){
		int result = userMapper.deleteById(1L);
		System.out.println(result);
	}

	/**
	 * 根据 id 批量删除
	 */
	@Test
	public void testDeleteBatchIds() {
		int result = userMapper.deleteBatchIds(Arrays.asList(1,2));
		System.out.println(result);
	}

	/**
	 * 测试性能分析插件
	 */
	@Test
	public void testPerformance() {
		User user = new User();
		user.setName("我是Helen");
		user.setEmail("helen@sina.com");
		user.setAge(18);
		userMapper.insert(user);
	}

	/**
	 * mp 实现复杂查询操作
	 */
	@Test
	public void testSelectQuery() {
		//创建QueryWrapper对象
		QueryWrapper<User> wrapper = new QueryWrapper<>();

		//通过 QueryWrapper 设置条件
		// ge、gt、le、lt >>>> 大于等于、大于、小于等于、小于
		// 查询 age>=30 的记录
		// 第一个参数字段名称，第二个参数设置值
	    // wrapper.ge("age", 30);

		// eq、ne >>>> 等于、不等于
		// wrapper.eq("name", "lilei");
		// wrapper.ne("name", "lilei");

		// between >>>> 指定范围
		// 查询年龄 20-30
		// wrapper.between("age", 20, 30);

		// like >>>> 模糊查询（包含指定内容）
		// wrapper.like("name", "岳"); // like `%岳%`

		// orderByDesc、orderByAsc >>> 降序、升序
		// wrapper.orderByDesc("id");

		// last >>>> 在 sql 后面拼接内容
		// wrapper.last("limit 1");

		// select >>>> 指定要查询的列
		wrapper.select("id", "name");

		List<User> users = userMapper.selectList(wrapper);
		System.out.println(users);
	}
}
