package com.cqc.security.bean;

import lombok.Data;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-17
 **/

@Data
public class AccessToken {

    private String token;

    private String refreshToken;

    private String tokenHead;

    private Long expiresIn;

    private String username;

    public AccessToken() {
    }

    public AccessToken(String token, String refreshToken, String tokenHead, Long expiresIn) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.tokenHead = tokenHead;
        this.expiresIn = expiresIn;
    }

}
