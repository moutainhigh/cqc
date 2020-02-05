package com.cqc.security.util;

import com.cqc.security.author.GoogleAuthenticator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GoogleAuthUtil {

    /**
     * 生产密钥
     * @return
     */
    public static String getSecret() {
        return GoogleAuthenticator.generateSecretKey();
    }

    /**
     * 生产二维码
     * @param account
     * @param secret
     * @return
     */
    public static String getQrcode(String account, String secret) {
       return GoogleAuthenticator.getQRBarcode(account, secret);
    }

    /**
     * 校验验证码
     * @param code
     * @param secret
     * @param timestamp
     * @return
     */
    public static boolean checkCode(String code, String secret, long timestamp) {
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5);
        boolean r = ga.check_code(secret, code, timestamp);
        return r;
    }
}
