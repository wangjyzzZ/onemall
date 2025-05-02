package com.wood.onemall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OnemallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnemallProductApplication.class, args);
    }

}
