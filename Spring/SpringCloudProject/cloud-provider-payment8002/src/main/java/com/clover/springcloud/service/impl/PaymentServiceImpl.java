package com.clover.springcloud.service.impl;

import com.clover.springcloud.mapper.PaymentDao;
import com.clover.springcloud.entities.Payment;
import com.clover.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(int id) {
        return paymentDao.getPaymentById(id);
    }
}
