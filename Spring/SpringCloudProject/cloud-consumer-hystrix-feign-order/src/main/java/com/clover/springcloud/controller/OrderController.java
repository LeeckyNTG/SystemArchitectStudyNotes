package com.clover.springcloud.controller;

import com.clover.springcloud.entities.CommonResult;
import com.clover.springcloud.entities.Payment;
import com.clover.springcloud.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    private static final String TAG = "OrderController";

    @Resource
    private OrderService orderService;

    @PostMapping(value = "consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info(TAG,payment);
        return orderService.create(payment);
    }

    @GetMapping(value = "consumer/payment/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") int id) {
        return orderService.getPayment(id);
    }

    @GetMapping(value = "consumer/payment/timeout")
    public String timeout() {
        return orderService.timeout();
    }


    @GetMapping("/consumer/ok/{id}")
    public String paymentInfo_OK(@PathVariable Integer id){
        return orderService.paymentInfo_OK(id);
    }

    @GetMapping("/payment/ok/{id}")
    public String paymentInfoOK(@PathVariable Integer id){
        return orderService.paymentInfoOK(id);
    }

    @GetMapping("/consumer/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable Integer id){
        log.info("timeout");
        String result = orderService.paymentInfo_Timeout(id);
        log.info(result);
        return result;
    }

}
