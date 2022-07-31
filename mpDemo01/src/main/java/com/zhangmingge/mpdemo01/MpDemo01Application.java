package com.zhangmingge.mpdemo01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhangmingge.mpdemo01.mapper") // 开启 mapper 扫描
public class MpDemo01Application {

	public static void main(String[] args)
	{
		SpringApplication.run(MpDemo01Application.class, args);
	}
}
