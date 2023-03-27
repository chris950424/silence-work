package com.silence;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 *  productApplication
 * 
 * @author leo
 * @version 1.1.0
 * @date 2022/1/18
 */
@MapperScan("com.silence.product.**.dao")
@ImportResource(locations = {"classpath:dubbo.xml"})
@SpringBootApplication
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
