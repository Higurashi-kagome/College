package com.zhangmingge.edumsm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

// TODO 一分钟过后重新获取验证码，应该要重新发送短信
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient // nacos 注册
@EnableFeignClients
@ComponentScan(basePackages = {"com.zhangmingge"})
public class MsmApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsmApplication.class, args);
	}
}
