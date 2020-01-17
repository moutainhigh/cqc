package com.cqc.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.User;
import com.cqc.portal.dto.ModifyAreaParam;
import com.cqc.portal.dto.ModifyPasswordParam;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author wanglz
 * @date 2020-01-08
 **/
public interface UserService extends IService<User> {

    /**
     * 获取可用用户
     * @param userId
     * @return
     */
    User getUser(String userId);

    /**
     * 修改登录密码
     * @param userId
     * @param param
     * @return
     */
    boolean modifyLoginPwd(String userId, ModifyPasswordParam param);

    /**
     * 修改支付密码
     * @param userId
     * @param param
     * @return
     */
    boolean modifyPayPwd(String userId, ModifyPasswordParam param);

    /**
     * 修改地区
     * @param userId
     * @param param
     * @return
     */
    boolean modifyArea(String userId, ModifyAreaParam param);
}
