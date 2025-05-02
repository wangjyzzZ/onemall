package com.wood.onemall.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OnemallWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnemallWareApplication.class, args);
    }

}
