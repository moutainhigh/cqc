package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.common.exception.BaseException;
import com.cqc.model.User;
import com.cqc.portal.dto.ModifyAreaParam;
import com.cqc.portal.dto.ModifyPasswordParam;
import com.cqc.portal.mapper.UserMapper;
import com.cqc.portal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author wanglz
 * @date 2020-01-08
 **/

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User getUser(String userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BaseException("", "不存在的用户");
        }
        if (user.getStatus() != 1) {
            throw new BaseException("", "用户被锁定");
        }
        return user;
    }

    @Override
    public boolean modifyLoginPwd(String userId, ModifyPasswordParam param) {
        // 首先校验旧密码
        User user = this.getUser(userId);
        String password = user.getPassword();
        if (!passwordEncoder.matches(password, param.getPassword())) {
            throw new BadCredentialsException("旧登录密码不正确");
        }
        User entity = new User();
        entity.setId(userId);
        entity.setPassword(passwordEncoder.encode(param.getNewPassword()));
        try {
            userMapper.updateById(entity);
            return true;
        } catch (Exception e) {
            log.error("修改登录密码错误 : \n {} ", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modifyPayPwd(String userId, ModifyPasswordParam param) {

        User user = this.getUser(userId);
        String payPwd = user.getPayPassword();
        if (!passwordEncoder.matches(payPwd, param.getPassword())) {
            throw new BadCredentialsException("旧支付密码不正确");
        }
        User entity = new User();
        entity.setId(userId);
        entity.setPayPassword(passwordEncoder.encode(param.getNewPassword()));
        try {
            userMapper.updateById(entity);
            return true;
        } catch (Exception e) {
            log.error("修改支付密码错误 : \n {} ", e.getMessage());
            return false;
        }
    }


    @Override
    public boolean modifyArea(String userId, ModifyAreaParam param) {
        User entity = new User();
        entity.setId(userId);
        entity.setProvince(param.getProvince());
        entity.setCity(param.getCity());
        entity.setRegion(param.getCity());

        try {
            userMapper.updateById(entity);
            return true;
        } catch (Exception e) {
            log.error("修改地区错误 : \n {} ", e.getMessage());
            return false;
        }
    }
}
