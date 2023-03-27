package com.silence;

import com.silence.auth.client.EnableAuthStarterConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.thymeleaf.context.IContext;

/**
 * AdminApplication
 *
 * @author leo
 * @version 1.1.0
 * @date 2021/12/25
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableAuthStarterConfiguration
@MapperScan("com.silence.admin.**.dao")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
