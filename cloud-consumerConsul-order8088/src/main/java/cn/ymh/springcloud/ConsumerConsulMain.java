package cn.ymh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerConsulMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerConsulMain.class,args);
    }
}
