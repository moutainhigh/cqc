package com.cqc.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dto.UmsAdminParam;
import com.cqc.admin.dto.UpdateAdminPasswordParam;
import com.cqc.model.UmsAdmin;
import com.cqc.model.UmsPermission;
import com.cqc.model.UmsRole;
import com.cqc.security.bean.AccessToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台管理员Service
 * Created by macro on 2018/4/26.
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    AccessToken login(String username, String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    AccessToken refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    UmsAdmin getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    Page<UmsAdmin> list(String name, Integer pageSize, Integer pageNum);


    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);


    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);
}
