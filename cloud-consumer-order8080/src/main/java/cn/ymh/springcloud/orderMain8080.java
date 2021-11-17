package cn.ymh.springcloud;

import cn.ymh.rule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableDiscoveryClient
//添加自定义的ribbon算法
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyselfRule.class)
public class orderMain8080 {
    public static void main(String[] args) {
        SpringApplication.run(orderMain8080.class,args);
    }
}
