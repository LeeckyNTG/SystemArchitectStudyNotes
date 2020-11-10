package com.clover.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircleBreakerController {

    @GetMapping("/fallback")
    @SentinelResource(value = "fallback", fallback = "exceptionFallback")
    public String fallback() {
        int i = 10/0;
        return "a";
    }
    public String exceptionFallback(Throwable e) {
        return "exceptionFallback";
    }




    @GetMapping("consumer/payment/{id}")
    @SentinelResource(value = "fallback", blockHandler = "exceptionBlockHandler")
    public String payment(@PathVariable("id") Long id) {
        return "aaaa";
    }

    public String exceptionBlockHandler(BlockException e) {
        return "exceptionBlockHandler";
    }

}
