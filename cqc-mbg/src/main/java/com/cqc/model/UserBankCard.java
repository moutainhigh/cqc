package com.cqc.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户-银行卡
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */

@Data
@TableName("user_bank_card")
public class UserBankCard implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行简称
     */
    private String bankCode;

    /**
     * 银行logo
     */
    private String bankLogo;

    /**
     * 银行卡类型：1-借记卡 2-贷记合一 3-贷记卡
     */
    private Integer cardType;

    /**
     * 银行卡号
     */
    private String cardNo;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 账户类型 1-个人账户 2企业账户
     */
    private Integer accountType;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态 0认证中 1认证通过 2 不通过
     */
    private Integer status;

    private Boolean isDefault;

    public String getCardNo() {
        if (!StringUtils.isEmpty(cardNo)) {
            if (cardNo.length() >= 4) {
                return "**** " + cardNo.substring(cardNo.length() - 4);
            } else {
                return "**** " + cardNo;
            }
        }
        return cardNo;
    }
}
