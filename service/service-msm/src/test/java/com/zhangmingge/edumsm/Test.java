package com.zhangmingge.edumsm;

import com.zhangmingge.edumsm.utils.ConstantPropertiesUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class Test {
	@org.junit.Test
	public void test(){
		System.out.println(ConstantPropertiesUtils.ACCESS_KEY_ID);
		System.out.println(ConstantPropertiesUtils.ACCESS_KEY_SECRET);
		System.out.println(ConstantPropertiesUtils.SIGN_NAME);
		System.out.println(ConstantPropertiesUtils.TEMPLATE_CODE);
	}
}
