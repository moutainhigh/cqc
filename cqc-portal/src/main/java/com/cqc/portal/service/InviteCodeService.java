package com.cqc.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.InviteCode;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
public interface InviteCodeService extends IService<InviteCode> {

    /**
     *
     * @param code
     * @return
     */
    String getUserId(String code);
}
