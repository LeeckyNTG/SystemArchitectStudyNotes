package com.clover.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.clover.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, String> hashMap = new HashMap<>();

    static {
        hashMap.put(1l, IdUtil.randomUUID());
        hashMap.put(2l, IdUtil.randomUUID());
        hashMap.put(3l, IdUtil.randomUUID());
    }

    @GetMapping("/payment/{id}")
    public CommonResult<String> payment(@PathVariable("id") Long id) {

        CommonResult<String> result = new CommonResult<>();
        result.setCode(200);
        result.setMessage("获取数据，服务器端口为：" + serverPort);
        result.setData(hashMap.get(id));
        return result;
    }
}
