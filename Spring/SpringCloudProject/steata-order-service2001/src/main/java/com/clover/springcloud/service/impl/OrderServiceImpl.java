package com.clover.springcloud.service.impl;

import com.clover.springcloud.entities.Order;
import com.clover.springcloud.mapper.OrderDao;
import com.clover.springcloud.service.AccountService;
import com.clover.springcloud.service.OrderService;
import com.clover.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "clover-order-create",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("------------->新建订单");
        orderDao.create(order);

        log.info("------------->订单服务调用库存做扣减start");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("------------->订单服务调用库存做扣减end");

        log.info("------------->账户做扣减start");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("------------->账户做扣减end");

        log.info("------------->修改订单状态start");
        orderDao.update(order.getUserId(),0);
        log.info("------------->修改订单状态end");

        log.info("------------->下订单结束了，^_^");
    }
}
