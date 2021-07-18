package com.king.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestProviderController {
    @GetMapping(value = "/service") //暴露服务
    public String service(){
        System.out.println("provider invoke");
        System.out.println("我执行了");
        return "provider invoke..... and 我执行了";
    }
}
