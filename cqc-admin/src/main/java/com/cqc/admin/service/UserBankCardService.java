package com.cqc.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.admin.dto.UserBankCardQueryParam;
import com.cqc.admin.dto.resp.UserBankCardListDto;
import com.cqc.model.UserBankCard;

/**
 * <p>
 * 用户-银行卡 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
public interface UserBankCardService extends IService<UserBankCard> {

    /**
     * 查询列表
     * @param param
     * @return
     */
    Page<UserBankCardListDto> pageList(UserBankCardQueryParam param);

}
