package com.cqc.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqc.admin.mapper.BankMapper;
import com.cqc.admin.service.BankService;
import com.cqc.model.Bank;
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
