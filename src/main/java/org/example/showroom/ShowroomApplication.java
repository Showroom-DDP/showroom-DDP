package org.example.showroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShowroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowroomApplication.class, args);
    }

}
