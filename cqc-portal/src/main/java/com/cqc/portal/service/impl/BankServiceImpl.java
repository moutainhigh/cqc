package com.cqc.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.model.Bank;
import com.cqc.portal.mapper.BankMapper;
import com.cqc.portal.service.BankService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 银行 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-01-17
 */
@Service
public class BankServiceImpl extends ServiceImpl<BankMapper, Bank> implements BankService {

}
