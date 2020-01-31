package com.cqc.portal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqc.common.exception.BaseException;
import com.cqc.model.User;
import com.cqc.model.UserFund;
import com.cqc.model.UserVirtualFund;
import com.cqc.portal.dto.RegisterParam;
import com.cqc.security.bean.AccessToken;
import com.cqc.security.bean.PortalUserDetails;
import com.cqc.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-17
 **/

@Slf4j
@Component
public class LoginRegisterService {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private InviteCodeService inviteCodeService;


    @Autowired
    private UserFundService userFundService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AccessToken login(String username, String password) {
        AccessToken accessToken = null;
        try {
            UserDetails userDetails = loadUser(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BaseException("", "密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            accessToken = jwtTokenUtil.generatePortalToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常： " + e.getMessage());
            throw new BaseException("", e.getMessage());
        }

        return accessToken;
    }

    @Transactional(rollbackFor = Throwable.class)
    public User register(RegisterParam param) {
        // 校验inviteCode
        String refUserId = "";
        String inviteCode = param.getInviteCode();
        if (!StringUtils.isEmpty(inviteCode)) {
            refUserId = inviteCodeService.getUserId(inviteCode);
            if (StringUtils.isEmpty(refUserId)) {
                throw new BaseException("", "邀请码错误");
            }
        }
        // 检查用户名是否重复
        if (!getByUserName(param.getAccount())) {
            throw new BaseException("", "该用户名已被注册");
        }
        if (!StringUtils.isEmpty(param.getMobile()) && !getByMobile(param.getMobile())) {
            throw new BaseException("", "该手机号码已被注册");
        }

        User user = new User();
        BeanUtils.copyProperties(param, user);
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //设置推荐人
        user.setRefUserId(refUserId);
        userService.save(user);
        // 初始化cqc数据
        UserFund fund = new UserFund(user.getId());
        userFundService.save(fund);

        return user;
    }

    public UserDetails loadUser(String username) {
        // 获取登录用户
        QueryWrapper wrapper = new QueryWrapper<User>().eq("account", username).or().eq("mobile", username);
        User user = userService.getOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return new PortalUserDetails(user);
    }

    private boolean getByUserName(String username) {
        QueryWrapper wrapper = new QueryWrapper<User>().eq("account", username);
        User user = userService.getOne(wrapper);
        return user == null;
    }
    private boolean getByMobile(String mobile) {
        QueryWrapper wrapper = new QueryWrapper<User>().eq("mobile", mobile);
        User user = userService.getOne(wrapper);
        return user == null;
    }
}
