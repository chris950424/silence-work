package com.silence;

import com.silence.auth.client.EnableAuthStarterConfiguration;
import com.silence.order.infrastructure.job.annotation.EnableElasticJob;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Administrator
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableElasticJob
@EnableAuthStarterConfiguration
@EnableFeignClients
@MapperScan("com.silence.order.**.dao")
public class OrderApplication {
    public static void main(String[] args) {
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(OrderApplication.class, args);
    }
}
