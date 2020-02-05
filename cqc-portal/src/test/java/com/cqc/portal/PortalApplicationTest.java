package com.cqc.portal;

import com.cqc.common.api.Result;
import com.cqc.common.constant.ConstantEnums;
import com.cqc.model.UserRecommend;
import com.cqc.portal.dto.RegisterParam;
import com.cqc.portal.manager.SmsService;
import com.cqc.portal.service.LoginRegisterService;
import com.cqc.portal.service.UserDateIncomeService;
import com.cqc.portal.service.UserService;
import com.cqc.portal.utils.EhcacheUtil;
import com.cqc.portal.utils.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={PortalApplication.class})
public class PortalApplicationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginRegisterService loginRegisterService;

    @Autowired
    private UserDateIncomeService userDateIncomeService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private EhcacheUtil ehcacheUtil;

    @Test
    public void findParent() {
        String userId = "2";
        List<UserRecommend> recommendList = userService.queryParents(userId);
        System.out.println(recommendList);
    }


    @Test
    public void testRegister() {
        RegisterParam param = new RegisterParam();
        param.setAccount("test2");
        param.setInviteCode("URZMHQZ7");
        param.setPassword("123456");
        param.setConfirmPassword("123456");
        param.setMobile("13590303545");

        loginRegisterService.register(param);
    }

    @Test
    public void testIncome() {
        String userId = "2d74b21b4d8a94f4de5ef63513576d7d";

        BigDecimal amount = new BigDecimal("40");
        String date = "20200204";

        userDateIncomeService.saveUserIncome(userId, amount, date);

    }

    @Test
    public void testSendSms() {
        String mobile = "13590303545";
        String key = mobile + "_1";
        String tmpId = ConstantEnums.SmsTmpId.REGISTER.getByType(1);
        // 生成验证码
        String code = RandomUtil.generateCode(4);
        try {
            Result<String> result = smsService.send(tmpId, code, mobile, 1);
            if (result.hashError()) {
            }
            ehcacheUtil.set(key, code, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testValidSms() {
        String mobile = "13590303545";
        String key = mobile + "_1";
        String code = ehcacheUtil.get(key);
        System.out.println(code);
    }

}
