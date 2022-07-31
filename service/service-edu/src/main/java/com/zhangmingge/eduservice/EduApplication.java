package com.zhangmingge.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * SpringBoot 启动类
 */
@SpringBootApplication
@EnableDiscoveryClient // nacos 注册
@EnableFeignClients
@ComponentScan(basePackages = {"com.zhangmingge"})
public class EduApplication {
	public static void main(String[] args) {
		SpringApplication.run(EduApplication.class, args);
	}
}