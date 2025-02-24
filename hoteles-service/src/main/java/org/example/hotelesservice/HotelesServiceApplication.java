package org.example.hotelesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HotelesServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelesServiceApplication.class, args);
    }
}
