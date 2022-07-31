package com.zhangmingge.eduucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient // nacos 注册
@ComponentScan(basePackages = {"com.zhangmingge"})
@SpringBootApplication
@MapperScan("com.zhangmingge.eduucenter.mapper")
public class UcenterApplication {
	public static void main(String[] args)
	{
		SpringApplication.run(UcenterApplication.class, args);
	}
}
