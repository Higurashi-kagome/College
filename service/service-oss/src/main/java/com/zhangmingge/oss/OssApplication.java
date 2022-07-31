package com.zhangmingge.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 不加载（排除）数据库
@EnableDiscoveryClient // nacos 注册
@ComponentScan(basePackages = {"com.zhangmingge"})
public class OssApplication {
	public static void main(String[] args)
	{
		SpringApplication.run(OssApplication.class, args);
	}
}
