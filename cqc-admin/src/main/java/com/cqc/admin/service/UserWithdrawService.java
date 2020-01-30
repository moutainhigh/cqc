package com.cqc.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.admin.dto.WithDrawQueryParam;
import com.cqc.admin.dto.resp.UserWithDrawDto;
import com.cqc.model.UserWithdraw;

/**
 * <p>
 * 提现记录 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-26
 */
public interface UserWithdrawService extends IService<UserWithdraw> {

    /**
     * 分页列表
     * @param param
     * @return
     */
    Page<UserWithDrawDto> listPage(WithDrawQueryParam param);

    /**
     * 退回
     * @param id
     * @return
     */
    boolean back(String id);
}
