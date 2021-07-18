package com.king.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class RestConsumerController {
    ///服务id即注册中心的中的服务名
    private String serviceId = "nacos_provider";
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/service")
    public String service() {
        RestTemplate restTemplate = new RestTemplate();
        //调用服务
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
        URI uri = serviceInstance.getUri();
        String providerResult = restTemplate.getForObject(uri + "/service", String.class);
        System.out.println("我被访问了，去掉提供方");
        return "consumer invoke | " + providerResult;
    }


    @GetMapping(value = "/login")
    public String login() {
        System.out.println("login");
        return "login";
    }

}
