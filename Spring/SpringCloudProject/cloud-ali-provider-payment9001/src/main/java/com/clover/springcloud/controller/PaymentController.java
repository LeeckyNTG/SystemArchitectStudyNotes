package com.clover.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value ="/payment/nacos/{id}")
    public String getPyment(@PathVariable("id") Integer id) {
        return "我是 nacos 注册,port:" + serverPort + "\t id:" + id;
    }

}
