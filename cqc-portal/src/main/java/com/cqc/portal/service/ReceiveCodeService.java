package com.cqc.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.ReceiveCode;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-18
 */
public interface ReceiveCodeService extends IService<ReceiveCode> {

    /**
     * 查询收款码
     * @param userId
     * @param channel
     * @return
     */
    ReceiveCode getCode(String userId, int channel);

    int getCodeNumber(String userId);
}
