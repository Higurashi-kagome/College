package com.zhangmingge.demo;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {

	@Test
	public void testWrite(){
		// 实现excel写的操作
		// 1 设置写入文件夹地址和excel文件名称
		String filename = "D:\\write.xlsx";
		// 2 调用easyexcel里面的方法实现写操作
		// write方法两个参数：第一个参数文件路径名称，第二个参数实体类class，sheet 方法设置 sheet 名称
		EasyExcel.write(filename, EasyExcelDemo.class).sheet("学生列表").doWrite(getData());
	}

	@Test
	public void testRead(){
		String filename = "D:\\write.xlsx";
		// 实现 excel 读操作
		EasyExcel.read(filename, EasyExcelDemo.class, new ExcelListener()).sheet().doRead();
	}


	/**
	 * 返回 list 集合
	 * @return
	 */
	private static List<EasyExcelDemo> getData() {
		List<EasyExcelDemo> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			EasyExcelDemo data = new EasyExcelDemo();
			data.setSno(i);          // 学生编号
			data.setSname("lucy"+i); // 学生姓名
			list.add(data);
		}
		return list;
	}
}
