package com.cqc.security.util;

import com.cqc.common.utils.ServletUtil;
import com.cqc.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-16
 **/
public class PortalUserUtil {

    private final static String BEARER = "Bearer";

    /**
     * 获取当前登录用户
     * @return
     */
    public static String getCurrentUsername() {
        return getParamFromToken("sub");
    }

    /**
     * 获取当前登录用户id
     * @return
     */
    public static String getCurrentUserId() {
        return getParamFromToken("user_id");
    }

    public static String getParamFromToken(String name) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey("cqc-portal-secret")
                    .parseClaimsJws(getToken())
                    .getBody();
            return String.valueOf(claims.get(name));
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取头部token
     * @return
     */
    private static String getToken() {
        String token = ServletUtil.getHeader( HttpHeaders.AUTHORIZATION);
        if(!StringUtils.hasText(token)) {
            return token;
        }
        String bearer = BEARER + " ";
        int pos = token.indexOf(bearer);
        if (pos == -1) {
            return "";
        }
        int length = bearer.length();
        return token.substring(length);
    }

}
