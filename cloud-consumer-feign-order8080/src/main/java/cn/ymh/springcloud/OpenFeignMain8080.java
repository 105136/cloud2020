package cn.ymh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenFeignMain8080 {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignMain8080.class,args);
    }
}
