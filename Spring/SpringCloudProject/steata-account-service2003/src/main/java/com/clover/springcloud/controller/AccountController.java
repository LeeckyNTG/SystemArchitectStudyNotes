package com.clover.springcloud.controller;

import com.clover.springcloud.entities.CommonResult;
import com.clover.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @GetMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") Integer money) {
        accountService.decrease(userId, money);


//        try{
//            Thread.sleep(20000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        return new CommonResult(200, "扣款成功");
    }

}
