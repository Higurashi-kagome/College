package com.zhangmingge.mpdemo02.mapper;

import com.zhangmingge.mpdemo02.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository // 加上注解，解决 @Autowired private UserMapper userMapper; 显红的问题（不加也能运行）
public interface UserMapper extends BaseMapper<User> {
}
