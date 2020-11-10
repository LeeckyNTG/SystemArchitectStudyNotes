package com.clover.springcloud.service.impl;

import com.clover.springcloud.entities.CommonResult;
import com.clover.springcloud.entities.Storage;
import com.clover.springcloud.mapper.StorageMapper;
import com.clover.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("-------------------->StorageService扣减库存开始");
        storageMapper.decrease(productId, count);
        log.info("-------------------->StorageService扣减库存结束");

    }
}
