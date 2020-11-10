package com.clover.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.clover.springcloud.mapper"})
public class MyBatisConfig {
}
