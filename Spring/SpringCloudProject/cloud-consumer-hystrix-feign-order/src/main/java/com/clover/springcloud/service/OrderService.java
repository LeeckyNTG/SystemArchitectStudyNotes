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
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE", fallback = OrderServiceImpl.class)
public interface OrderService {

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment);

    @GetMapping(value = "/payment/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") int id);

    @GetMapping("/timeout")
    public String timeout();

    @GetMapping("/ok/{id}")
    public String paymentInfo_OK(@PathVariable(value = "id") Integer id);

    @GetMapping("/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id);

    @GetMapping("/payment/ok/{id}")
    public String paymentInfoOK(@PathVariable("id") Integer id);

}
