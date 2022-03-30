package com.example.demo.Controller;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/h")
    public String getHomePage(){
        return "root";
    }

}