package com.app.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication(exclude={SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@SpringBootApplication
public class AppApplication {
    public static void main(String[] args){
        SpringApplication.run(AppApplication.class, args);
    }
}
