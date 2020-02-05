package com.cqc.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqc.admin.dto.UserBankCardQueryParam;
import com.cqc.admin.dto.resp.UserBankCardListDto;
import com.cqc.model.UserBankCard;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户-银行卡 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
public interface UserBankCardMapper extends BaseMapper<UserBankCard> {

    /**
     * 列表
     * @param page
     * @param param
     * @return
     */
    Page<UserBankCardListDto> pageList(Page<UserBankCardListDto> page, @Param("p") UserBankCardQueryParam param);
}
