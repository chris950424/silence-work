package com.silence;

import com.silence.auth.client.EnableAuthStarterConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableAuthStarterConfiguration
@EnableDiscoveryClient
@MapperScan("com.silence.elasticsearch.**.dao")
public class ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }

}
