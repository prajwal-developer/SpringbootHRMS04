package com.kodnest.project.Controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
	

    @Value("${sarvam.api.key}")
    private String apiKey;

    @GetMapping("/ai/chat")
    public String chat() {
        return "AI Controller Working";
    }
    
    
}
