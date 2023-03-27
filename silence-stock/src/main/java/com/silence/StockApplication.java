package com.silence;

import com.silence.auth.client.EnableAuthStarterConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *  productApplication
 * 
 * @author leo
 * @version 1.1.0
 * @date 2022/1/18
 */
@MapperScan("com.silence.stock.**.dao")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAuthStarterConfiguration
public class StockApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }
}
