package com.zhangmingge.edumsm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaModel;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.zhangmingge.edumsm.service.MsmService;
import com.zhangmingge.edumsm.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {

    public com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    @Override
    public boolean send(Map<String, Object> param, String phone){
        try {
            Client client = createClient(
                    ConstantPropertiesUtils.ACCESS_KEY_ID,
                    ConstantPropertiesUtils.ACCESS_KEY_SECRET);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setSignName(ConstantPropertiesUtils.SIGN_NAME)
                    .setTemplateCode(ConstantPropertiesUtils.TEMPLATE_CODE)
                    .setPhoneNumbers(phone)
                    .setTemplateParam(JSONObject.toJSONString(param));
            RuntimeOptions runtime = new RuntimeOptions();
            SendSmsResponse resp = client.sendSmsWithOptions(sendSmsRequest, runtime);
            System.out.println(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(resp)));
            return "OK".equals(resp.getBody().getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
