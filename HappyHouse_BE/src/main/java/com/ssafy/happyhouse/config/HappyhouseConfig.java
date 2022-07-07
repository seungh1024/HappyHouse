package com.ssafy.happyhouse.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@MapperScan(basePackages =  {"com.ssafy.happyhouse.model.mapper"})
public class HappyhouseConfig {

}
