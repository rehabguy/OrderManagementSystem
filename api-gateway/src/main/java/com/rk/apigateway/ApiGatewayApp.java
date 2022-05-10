package com.rk.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApp.class, args);
    }

}


//Observation ---
//
//Called url "localhost:8080/api/product" but product-service not running on 8080(port of api-gateway),
//        still it shows correct result which
//proves that request is routing via api-gateway

//so in all cases "localhost:8080" is fixed and "/api/{service-name}" is variable