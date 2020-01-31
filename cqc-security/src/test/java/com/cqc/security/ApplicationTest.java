package com.cqc.security;

import com.cqc.security.author.GoogleAuthenticator;
import com.cqc.security.util.GoogleAuthUtil;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Scanner;

/**
 * 测试类
 */
public class ApplicationTest {
    //当测试authTest时候，把genSecretTest生成的secret值赋值给它
    private static String secret="R2Q3S52RNXBTFTOM";

    //@Test

    /**
     * 对app的随机生成的code,输入并验证
     */
    public void verifyTest() {

    }

    public static void main2(String[] args) throws IOException {
        String secret = GoogleAuthenticator.generateSecretKey();
        String qrcode = GoogleAuthenticator.getQRBarcode("2816661736@qq.com", secret);
        System.out.println("qrcode:" + qrcode + ",key:" + secret);
        Scanner scanner = new Scanner(System.in);
        String nextLine = scanner.nextLine();

        long code = Long.parseLong(nextLine);

        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5);
        boolean r = ga.check_code(secret, code, t);
        System.out.println("检查code是否正确？" + r);

    }


    public static void main(String[] args) {
        String secret = "AWGVOMS4N7WLFFNX";
        boolean b = GoogleAuthUtil.checkCode(921746, secret, System.currentTimeMillis());
        System.out.println(b);
    }
}
