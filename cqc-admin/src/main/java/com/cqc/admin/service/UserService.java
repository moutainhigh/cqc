package com.cqc.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.admin.dto.UserAddParam;
import com.cqc.admin.dto.UserQueryParam;
import com.cqc.admin.dto.resp.UserListDto;
import com.cqc.model.User;

/**
 * @author wanglz
 * @date 2020-01-08
 **/
public interface UserService extends IService<User> {

    /**
     * 分页查询
     * @param param
     * @return
     */
    Page<UserListDto> listPage(UserQueryParam param);

    /**
     *
     * @param param
     * @return
     */
    boolean addUser(UserAddParam param);

    /**
     * 关闭用户
     * @param userId
     * @param closeTime
     * @return
     */
    boolean close(String userId, String closeTime);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    boolean delete(String userId);

}
