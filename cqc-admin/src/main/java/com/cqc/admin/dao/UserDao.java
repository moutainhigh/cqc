package com.cqc.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dto.UserQueryParam;
import com.cqc.admin.dto.resp.UserListDto;
import com.cqc.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;


/**
 * @author ZZT
 */
    public interface UserDao extends BaseMapper<User> {

    /**
     * 查询用户列表
     * @param page
     * @param p
     * @return
     */
    Page<UserListDto> list(Page<UserListDto> page, @Param("p") UserQueryParam p);
}