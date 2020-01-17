package com.cqc.portal.service.impl;

import com.cqc.portal.api.model.User;
import com.cqc.portal.mapper.UserMapper;
import com.cqc.portal.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
