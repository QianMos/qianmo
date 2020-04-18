package com.qianmo.untils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Author: QianMo
 * @Date: Create in 17:08 2020/4/6
 */
/*短信发送工具类*/
@Component
public class SmsUntil {
    @Autowired
    Environment env;//获取yml中的配置文件
    public String sendSms(String phone,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", env.getProperty("aliyun.sms.accessKeyId"), env.getProperty("aliyun.sms.accessKeySecret"));
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", env.getProperty("aliyun.sms.SignName"));
        request.putQueryParameter("TemplateCode", env.getProperty("aliyun.sms.TemplateCode"));
        request.putQueryParameter("TemplateParam","{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getData();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
