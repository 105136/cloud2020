package cn.ymh.springcloud.controller;

import cn.ymh.springcloud.config.LoadBalance;
import cn.ymh.springcloud.entities.CommonResult;
import cn.ymh.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController
@Slf4j
public class orderController {

    //private static final String PAYMENT_URL = "http://localhost:8001";
//    spring.application.name = cloud-payment-service
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalance loadBalance;

    @Resource
    private DiscoveryClient discoveryClient;

    //getObject就只是json字符串
    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    //getEntity是包括响应头、响应体等信息的
    @GetMapping(value = "/consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getPaymentByEntity(@PathVariable Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
        log.info(entity.getStatusCode()+"\t " + entity.getHeaders());
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<Payment>(400,"调用失败");
        }
    }

    @PostMapping(value = "/consumer/payment/create")
    public CommonResult<Payment>  create(Payment payment){
        log.info(payment.toString());
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    //手写负载均衡测试，显示8001、8002
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");

        if(instances == null || instances.size() == 0 ){
            return null;
        }

        ServiceInstance serviceInstance = loadBalance.instance(instances);

        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
}
