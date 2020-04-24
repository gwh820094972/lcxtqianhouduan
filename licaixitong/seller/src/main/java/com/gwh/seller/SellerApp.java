package com.gwh.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
/**
 * 销售端启动类
 */
@SpringBootApplication
@EntityScan("com.gwh.entity")
public class  SellerApp {
    public static void main(String[] args) {
        SpringApplication.run(SellerApp.class);
    }
}
