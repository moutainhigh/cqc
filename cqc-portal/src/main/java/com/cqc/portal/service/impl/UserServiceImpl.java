package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.common.api.ResultCode;
import com.cqc.common.exception.BaseException;
import com.cqc.model.User;
import com.cqc.model.UserRecommend;
import com.cqc.portal.dto.ModifyAreaParam;
import com.cqc.portal.dto.ModifyPasswordParam;
import com.cqc.portal.mapper.UserMapper;
import com.cqc.portal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
        return user;
    }


    @Override
    public User getByMobile(String mobile) {
        QueryWrapper wrapper = new QueryWrapper<User>().eq("mobile", mobile);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public User checkUser(String userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BaseException("", "不存在的用户");
        }
        if (user.getStatus() == -1) {
            throw new BaseException("", "用户不存在");
        }
        if (user.getStatus() == 2) {
            if (user.getCloseTime() != null && user.getCloseTime().after(new Date())) {
                throw new BaseException(ResultCode.CLOSE);
            }
        }
        return user;
    }

    @Override
    public boolean modifyLoginPwd(String userId, ModifyPasswordParam param) {
        // 首先校验旧密码
        User user = this.getUser(userId);
        String password = user.getPassword();
        if (!passwordEncoder.matches(param.getPassword(), password)) {
            throw new BaseException("500","旧登录密码不正确");
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

        // 如果有旧密码  需要校验旧密码
        if (!StringUtils.isEmpty(user.getPayPassword())) {
            String payPwd = user.getPayPassword();
            if (!passwordEncoder.matches(param.getPassword(), payPwd)) {
                throw new BadCredentialsException("旧支付密码不正确");
            }
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

    @Override
    public boolean openCloseAutoOrder(String userId, int autoOrder) {
        // 更改表状态
        try {
            userMapper.updateAutoOrder(userId, autoOrder);
            return true;
        } catch (Exception e) {
            log.error("开启/关闭自动抢单错误 : \n {} ", e.getMessage());
            return false;
        }

    }

    @Override
    public List<UserRecommend> queryParents(String userId) {
        User user = userMapper.selectById(userId);
        if (null == user) {
            return null;
        }
        LinkedList<UserRecommend> list = new LinkedList<>();
        findParent(list, user);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        for (UserRecommend userRecommend : list) {
            userRecommend.setUserId(user.getId());
            userRecommend.setAccount(user.getAccount());
        }
        return list;
    }

    @Override
    public List<User> getByParent(String parentUserId) {
        List<User> userList = userMapper.selectList(new QueryWrapper<User>().eq("ref_user_id", parentUserId));
        return userList;
    }

    private void findParent(LinkedList<UserRecommend> list, User user) {
        if (user == null || StringUtils.isEmpty(user.getRefUserId())) {
            return;
        }
        String parentId = user.getRefUserId();
        User parent = findParent(parentId);
        if (parent == null) {
            return;
        }
        int size = list.size();
        UserRecommend userRecommend = new UserRecommend();
        userRecommend.setLevel(++size);
        userRecommend.setRefUserId(parent.getId());
        userRecommend.setRefUserAccount(parent.getAccount());

        list.add(userRecommend);

        findParent(list, parent);
    }

    private User findParent(String refUserId) {
        return userMapper.selectById(refUserId);
    }


    @Override
    public boolean checkMobileReg(String mobile) {
        QueryWrapper wrapper = new QueryWrapper<User>().eq("mobile", mobile);
        User user = userMapper.selectOne(wrapper);
        return user != null;
    }
}
