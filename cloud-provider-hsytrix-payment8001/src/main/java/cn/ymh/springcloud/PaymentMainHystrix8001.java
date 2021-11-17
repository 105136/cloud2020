package cn.ymh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.swing.*;

@SpringBootApplication
@EnableEurekaClient
public class PaymentMainHystrix8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMainHystrix8001.class,args);
    }
}
