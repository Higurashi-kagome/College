package com.zhangmingge.edumsm.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

// spring中存在一个 InitializingBean 接口，项目一启动，spring 加载之后，接口方法就会执行
@Component
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${aliyun.msm.keyid}")
    private String keyId;

    @Value("${aliyun.msm.keysecret}")
    private String keySecret;

    @Value("${aliyun.msm.SignName}")
    private String SignName;

    @Value("${aliyun.msm.TemplateCode}")
    private String TemplateCode;

    //定义公开静态常量
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String SIGN_NAME;
    public static String TEMPLATE_CODE;

    /**
     * 在 spring 注入属性后执行
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        SIGN_NAME = SignName;
        TEMPLATE_CODE = TemplateCode;
    }
}
