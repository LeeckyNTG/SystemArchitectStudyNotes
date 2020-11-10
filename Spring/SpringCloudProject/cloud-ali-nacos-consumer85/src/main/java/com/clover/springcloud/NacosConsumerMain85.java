package com.clover.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerMain85 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerMain85.class, args);
    }
}
