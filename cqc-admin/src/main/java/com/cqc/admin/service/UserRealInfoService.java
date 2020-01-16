package com.cqc.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.admin.dto.UserRealInfoQueryParam;
import com.cqc.admin.dto.resp.UserRealInfoDto;
import com.cqc.model.UserRealInfo;

/**
 * <p>
 * 用户实名信息 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
public interface UserRealInfoService extends IService<UserRealInfo> {


    /**
     * 实名认证列表
     * @return
     */
    Page<UserRealInfoDto> listPage(UserRealInfoQueryParam param);

}
