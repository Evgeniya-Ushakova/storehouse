package com.evg.storehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StorehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorehouseApplication.class, args);
    }

}
