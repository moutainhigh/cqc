package com.cqc.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqc.model.User;
import com.cqc.model.UserRecommend;
import com.cqc.portal.dto.ModifyAreaParam;
import com.cqc.portal.dto.ModifyPasswordParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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
     * 获取可用的用户
     * @param userId
     * @return
     */
    User checkUser(String userId);

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

    /**
     * 开启/关闭自动抢单
     * @param userId
     * @param autoOrder
     * @return
     */
    boolean openCloseAutoOrder(String userId, int autoOrder);

    /**
     * 查找父级
     * @param userId
     * @return
     */
    List<UserRecommend> queryParents(String userId);

    /**
     * 上级id
     * @param parentUserId
     * @return
     */
    List<User> getByParent(String parentUserId);

    /**
     * 检查手机号码是否注册
     * @param mobile
     * @return
     */
    boolean checkMobileReg(String mobile);
}
