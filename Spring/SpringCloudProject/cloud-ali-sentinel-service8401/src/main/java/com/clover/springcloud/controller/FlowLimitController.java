package com.clover.springcloud.controller;

import cn.hutool.core.date.DateTime;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() {
//        try {
//            TimeUnit.MILLISECONDS.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(DateTime.now().toString());
        return "testB";
    }

    @GetMapping("/testC")
    public String testC() {
        int a = 10 / 0;
        return "testC";
    }

    @GetMapping("/testD/{id}")
    public String testD(@PathVariable("id") Integer id) {
        log.info("*************id:" + id);
        if (id < 0) {
            log.info("***************************error");
            throw new RuntimeException();
        }
        return "testD";
    }
//    @GetMapping("/testD")
//    public String testD(){
//        try {
//            TimeUnit.MILLISECONDS.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return "testD";
//    }

    @GetMapping("/testHotKey")
    // 名字可以随意起，但为唯一标识
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    // required 表示是否必须包含此参数
    public String testHostKey(@RequestParam(value = "p1", required = false) String p1,
                              @RequestParam(value = "p2", required = false) String p2) {
        System.out.println(p1);
//        int i = 10 / 0;
        return "testHostKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "deal_testHotKey";
    }
}
