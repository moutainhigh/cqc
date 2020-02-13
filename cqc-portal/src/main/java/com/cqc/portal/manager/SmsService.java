package com.cqc.portal.manager;

import com.cqc.common.api.Result;
import com.cqc.common.utils.JsonUtil;
import com.cqc.common.utils.MD5Util;
import com.cqc.portal.utils.EhcacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送短信验证码处理
 */
@Service
public class SmsService {

    @Value("${sms.uri}")
    private String addr;

    @Value("${sms.user_id}")
    private String userId;

    @Value("${sms.pwd}")
    private String pwd;

    private static final String encode = "utf8";

    @Autowired
    private EhcacheUtil ehcacheUtil;

    public Result<String> send(String tmpId, String code, String mobile, int type) throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        String msgContent = JsonUtil.obj2String(map);

        //组建请求
        String straddr = addr +
                "?ac=send&uid=" + userId+
                "&pwd=" + MD5Util.MD5Encode(pwd+userId) +
                "&mobile=" + mobile +
                "&encode=" + encode +
                "&content=" + msgContent+
                "&template="+ tmpId;

        StringBuffer sb = new StringBuffer(straddr);
        System.out.println("URL:" + sb);

        //发送请求
        URL url = new URL(sb.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                url.openStream()));

        //返回结果
        String inputline = in.readLine();
        Map<String, String> sendResult = JsonUtil.string2Obj(inputline, Map.class);
        String resultCode = sendResult.get("stat");
        String resultMsg = sendResult.get("message");
        if ("100".equals(resultCode)) {
            // 发送成功  保存到ehcache中
            String key = mobile + "_" + type;
            ehcacheUtil.set(key, code, 300);
            return Result.success(resultMsg);
        }
        return new Result<>(resultCode, resultMsg, null);
    }

    public boolean validSmsCode(String code, String mobile, int type) {
        String key = mobile + "_" + type;
        String cacheCode = ehcacheUtil.get(key);
        if (code.equals(cacheCode)) {
            return true;
        }
        return false;
    }
}
