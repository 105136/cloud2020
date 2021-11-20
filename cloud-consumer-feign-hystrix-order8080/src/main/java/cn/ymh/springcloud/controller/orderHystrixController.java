package cn.ymh.springcloud.controller;

import cn.ymh.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_PaymentMethod")
public class orderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_ok(id);
        return result;
    }


    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeout_FallbackMethod"/*定制化fallback*/,commandProperties = {
////            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
////    })
    @HystrixCommand //不配置默认使用全局的fallback方法
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
//        int i = 10/0;
        String result = paymentHystrixService.paymentInfo_timeout(id);
        return result;
    }

    //善后方法
    public String paymentInfoTimeout_FallbackMethod(Integer id){
        return "我是消费者80，对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    public String payment_Global_PaymentMethod(){
        return "我是消费者80，默认全局配置的fallback";
    }
}
