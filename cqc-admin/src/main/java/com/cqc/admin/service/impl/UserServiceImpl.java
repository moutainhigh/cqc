package com.cqc.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.admin.dao.UserDao;
import com.cqc.admin.dto.UserAddParam;
import com.cqc.admin.dto.UserQueryParam;
import com.cqc.admin.dto.resp.UserListDto;
import com.cqc.admin.service.UserFundService;
import com.cqc.admin.service.UserRateService;
import com.cqc.admin.service.UserService;
import com.cqc.common.exception.BaseException;
import com.cqc.model.User;
import com.cqc.model.UserFund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wanglz
 * @date 2020-01-08
 **/

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userMapper;

    @Autowired
    private UserRateService userRateService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserFundService userFundService;

    @Override
    public Page<UserListDto> listPage(UserQueryParam param) {
        Page<UserListDto> page = new Page<>(param.getPageNum(), param.getPageSize());
        userMapper.list(page, param);
        return page;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean addUser(UserAddParam param) {
        Integer count = userMapper.selectCount(new QueryWrapper<User>().eq("account", param.getAccount()));
        if (count >= 1) {
            throw new BaseException("", "用户名重复");
        }
        User user = new User();
        user.setAccount(param.getAccount());
        user.setPassword(passwordEncoder.encode(param.getPassword()));
        user.setStatus(1);
        int i = userMapper.insert(user);

        // 初始化费率
        boolean b = userRateService.initUserRate(user.getId());
        if (!b) {
            // 回滚数据
            throw new BaseException("", "添加用户失败");
        }
        // 初始化UserFund
        UserFund userFund = new UserFund(user.getId());
        boolean rs = userFundService.save(userFund);
        if (!rs) {
            // 回滚数据
            throw new BaseException("", "添加用户失败");
        }
        return i == 1;
    }

    @Override
    public boolean close(String userId, String closeTime) {



        return false;
    }

    @Override
    public boolean delete(String userId) {
        return false;
    }
}
