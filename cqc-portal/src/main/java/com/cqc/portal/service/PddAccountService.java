package com.cqc.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.PddAccount;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-10
 */
public interface PddAccountService extends IService<PddAccount> {

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    List<PddAccount> getByUser(String userId);

}
