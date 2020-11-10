package com.clover.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.clover.springcloud.entities.CommonResult;
import com.clover.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;


    //fallback处理运行时异常，blockHandler处理Sentinel限流异常
    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")   // 没有配置
//    @SentinelResource(value = "fallback",blockHandler = "blockHandler")//blockhandler
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
//fallback+blockhandler
//    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler", exceptionsToIgnore = RuntimeException.class)

//    @SentinelResource(value = "fallback",fallback = "handlerFallback")

//    @SentinelResource(value = "fallback", blockHandler = "blockHandler")
    public CommonResult<String> fallback(@PathVariable("id") Long id) {
        CommonResult<String> result = restTemplate.getForObject(SERVICE_URL + "/payment/" + id, CommonResult.class, id);
        if (id == 4) {
            throw new RuntimeException("非法参数异常");
        } else if (result.getData() == null) {
            throw new RuntimeException("该ID没有记录，空指针异常");
        }
        return result;
    }

    /**
     * fallback异常
     *
     * @param id
     * @return
     */
    public CommonResult<String> handlerFallback(@PathVariable Long id, Throwable e) {
        return new CommonResult<>(444, id + "handlerFallback异常" + e.getMessage());
    }

    public CommonResult<String> blockHandler(@PathVariable Long id, BlockException e) {
        return new CommonResult<>(444, "blockHandler异常" + e.getMessage());
    }

    @Resource
    private PaymentService paymentService;

    @GetMapping("consumer/payment/{id}")
    public String payment(@PathVariable("id") Long id) {
        return paymentService.payment(id);
    }
}
