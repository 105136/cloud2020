package cn.ymh.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced   //RestTemplate要结合@LoadBalanced实现负载均衡的调用
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
