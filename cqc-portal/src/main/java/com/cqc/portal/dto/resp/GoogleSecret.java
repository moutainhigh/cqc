package com.cqc.portal.dto.resp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GoogleSecret {

    private String qrCode;

    private String secret;

    public GoogleSecret(String qrCode, String secret) {
        this.qrCode = qrCode;
        this.secret = secret;
    }
}
