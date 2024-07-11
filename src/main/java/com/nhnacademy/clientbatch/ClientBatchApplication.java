package com.nhnacademy.clientbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ClientBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientBatchApplication.class, args);
    }

}
