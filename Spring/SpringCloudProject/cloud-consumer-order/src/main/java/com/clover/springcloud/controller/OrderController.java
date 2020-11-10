package com.clover.springcloud.controller;

import com.clover.springcloud.entities.CommonResult;
import com.clover.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    private static final String TAG = "OrderController";

    //    public static final String PAYMENT_URL = "http://localhost:8001/";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping(value = "consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info(TAG, payment);
        log.info(TAG + payment);
        return restTemplate.postForObject(PAYMENT_URL + "payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "consumer/payment/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") int id) {
        return restTemplate.getForObject(PAYMENT_URL + "payment/getPayment/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin() {
        String result = restTemplate.getForObject(PAYMENT_URL + "/payment/zipkin", String.class);
        return result;
    }
}
