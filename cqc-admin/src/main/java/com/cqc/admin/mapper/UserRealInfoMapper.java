package com.cqc.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dto.resp.UserRealInfoDto;
import com.cqc.model.UserRealInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户实名信息 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-01-16
 */
public interface UserRealInfoMapper extends BaseMapper<UserRealInfo> {


    /**
     *
     * @param page
     * @param status
     * @return
     */
    Page<UserRealInfoDto> listPage(Page<UserRealInfoDto> page, @Param("type") int type, @Param("status") Integer status);

}
