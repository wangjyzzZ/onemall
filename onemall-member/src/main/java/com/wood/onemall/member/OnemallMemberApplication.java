package com.wood.onemall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.wood.onemall.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class OnemallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnemallMemberApplication.class, args);
    }

}
