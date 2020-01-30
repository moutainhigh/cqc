package com.cqc.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dto.WithDrawQueryParam;
import com.cqc.admin.dto.resp.UserWithDrawDto;
import com.cqc.model.UserWithdraw;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 提现记录 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-01-26
 */
public interface UserWithdrawMapper extends BaseMapper<UserWithdraw> {

    /**
     * 分页查询提现记录
     * @param page
     * @param param
     * @return
     */
    Page<UserWithDrawDto> pageList(Page<UserWithDrawDto> page, @Param("p") WithDrawQueryParam param);

}
