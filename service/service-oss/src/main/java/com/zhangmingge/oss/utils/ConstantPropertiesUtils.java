package com.zhangmingge.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// spring中存在一个 InitializingBean 接口，项目一启动，spring 加载之后，接口方法就会执行
@Component
public class ConstantPropertiesUtils {
    //定义公开静态常量
    public static String END_POINT = "oss-cn-hangzhou.aliyuncs.com";
    public static String ACCESS_KEY_ID = "LTAI5tJYqqAWSH4LvaaZFDB8";
    public static String ACCESS_KEY_SECRET = "5GEkmK6a7Ghfcu961srvO9QblMdnzU";
    public static String BUCKET_NAME = "college-1010";
}
