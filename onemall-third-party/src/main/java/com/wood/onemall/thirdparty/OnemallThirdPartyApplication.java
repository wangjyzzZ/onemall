package com.wood.onemall.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OnemallThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnemallThirdPartyApplication.class, args);
    }

}
