package com.clover.springcloud.service;

import com.clover.springcloud.entities.CommonResult;
import com.clover.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class OrderServiceImpl implements OrderService{

    String errorMsg = "server error";


    @Override
    public CommonResult create(Payment payment) {
        return new CommonResult(444,errorMsg);
    }

    @Override
    public CommonResult<Payment> getPayment(int id) {
        return new CommonResult(444,errorMsg);
    }

    @Override
    public String timeout() {
        return errorMsg;
    }

    @Override
    public String paymentInfo_OK(Integer id) {
        return errorMsg;
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return errorMsg;
    }

    @Override
    public String paymentInfoOK(Integer id) {
        return errorMsg;
    }
}
