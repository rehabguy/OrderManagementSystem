package com.rk.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// use this for notes https://docs.google.com/document/d/1fFYc7ckJr6cjvtWbB_uw_uzr5YNDgU9fNEuK3ekCOfw/edit

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApp {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApp.class, args);
    }

}
