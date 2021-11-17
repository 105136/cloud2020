package cn.ymh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderZkMain8080 {
    public static void main(String[] args) {
        SpringApplication.run(OrderZkMain8080.class,args);
    }
}
