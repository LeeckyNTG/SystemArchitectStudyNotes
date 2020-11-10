package com.clover.springcloud.controller;


import com.clover.springcloud.entities.CommonResult;
import com.clover.springcloud.entities.Payment;
import com.clover.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;


/**
  * @Description    :  支付业务处理
  * @Author         :  clover
  * @CreateBy       :  8/25/20 14:50
  * @FileName       :  PaymentController.java
  * @ModifierDate   :  
  * @ModifierAuthor :
  */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*************插入数据" + payment);
        log.info("*************插入结果" + result);
        if (result > 0) {
            return new CommonResult(200, "success,port" + serverPort, result);
        } else {
            return new CommonResult(444, "error");
        }
    }

    @GetMapping(value = "/payment/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") int id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("*************获取数据" + result);
        if (result != null) {
            return new CommonResult(200, "success,port" + serverPort, result);
        } else {
            return new CommonResult(444, "error");
        }
    }


    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("********element:"+service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping("/timeout")
    public String timeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "成功";
    }


    @GetMapping("/ok/{id}")
    public String paymentInfo_OK(@PathVariable Integer id){
        return paymentService.paymentInfo_OK(id);
    }

    @GetMapping("/payment/ok/{id}")
    public String paymentInfoOK(@PathVariable Integer id){
        return paymentService.payment_OK(id);
    }

    @GetMapping("/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable Integer id){
        log.info("timeout");
        String result = paymentService.paymentInfo_Timeout(id);
        log.info(result);
        return result;
    }
    @GetMapping("/payment/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable Integer id){
        log.info("timeout");
        String result = paymentService.paymentInfo_Timeout(id);
        log.info(result);
        return result;
    }

    // 服务熔断
    @GetMapping("/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("************"+result);
        return result;
    }



}
