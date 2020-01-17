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

    private String refreshTokenHead;


}
