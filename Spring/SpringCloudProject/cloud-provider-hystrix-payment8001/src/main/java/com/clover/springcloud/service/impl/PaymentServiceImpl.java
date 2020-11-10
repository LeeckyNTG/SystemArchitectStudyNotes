package com.clover.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.clover.springcloud.mapper.PaymentDao;
import com.clover.springcloud.entities.Payment;
import com.clover.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(int id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "ok" + id;
    }


    @Override
    public String payment_OK(Integer id) {

        int timeNumber = id;

//        int age = 10/0;

        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程池：" + Thread.currentThread().getName() + "Timeout"+timeNumber+"\t耗时：3秒";

    }


    /**
     * 超时和代码异常服务降级
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @Override
    public String paymentInfo_Timeout(Integer id) {
        int timeNumber = id;
//        int age = 10/0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "Timeout" + id + "\t耗时：" + timeNumber + "秒";
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "\t8001服务异常降级处理😭";
    }

    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentInfo_Circuit",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),// 时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")// 失败率
            // 加起来就是在10s内的10次请求中如果失败超过6次进入服务熔断
    })
    @Override
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id<0){
            throw new RuntimeException("id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return "调用成功："+serialNumber;
    }

    public String paymentInfo_Circuit(Integer id){
        return "id不能为负数："+id;
    }


}
