package com.clover.springcloud.service.impl;

import com.clover.springcloud.mapper.AccountMapper;
import com.clover.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public void decrease(Long userId, Integer count) {
        log.info("-------------------->StorageService扣减账户金额开始");
        accountMapper.decrease(userId, count);
        log.info("-------------------->StorageService扣减账户金额结束");

    }
}
