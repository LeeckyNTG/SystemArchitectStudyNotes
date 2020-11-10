package com.clover.springcloud.service;

import com.clover.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") int id);

    public String paymentInfo_Timeout(Integer id);

    public String paymentInfo_OK(Integer id);

    public String payment_OK(Integer id);

    public String paymentCircuitBreaker(@PathVariable("id") Integer id);

}
