package com.cqc.security.util;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-16
 **/
public class PortalUserUtil {


    /**
     * 获取当前登录用户
     * @return
     */
    public static User getCurrentUser() {
        return new User();
    }

    /**
     * 获取当前登录用户id
     * @return
     */
    public static String getCurrentUserId() {
        return "1";
    }




}
