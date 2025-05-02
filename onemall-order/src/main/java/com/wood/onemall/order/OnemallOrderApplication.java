package com.wood.onemall.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OnemallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnemallOrderApplication.class, args);
    }

}
