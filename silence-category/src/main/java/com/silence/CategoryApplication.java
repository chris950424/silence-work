package com.silence;

import com.silence.auth.client.EnableAuthStarterConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

/**
 *  CategoryApplication
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/6
 */
@SpringBootApplication
@EnableAuthStarterConfiguration
@EnableDiscoveryClient
@MapperScan("com.silence.category.**.dao")
public class CategoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(CategoryApplication.class, args);
    }
}
