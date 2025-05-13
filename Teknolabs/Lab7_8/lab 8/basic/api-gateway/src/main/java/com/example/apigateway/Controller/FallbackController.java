package com.example.apigateway.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/products")
    public Map<String, String> productServiceFallback() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Product Service is currently unavailable. Please try again later.");
        return response;
    }

    @GetMapping("/orders")
    public Map<String, String> orderServiceFallback() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Order Service is currently unavailable. Please try again later.");
        return response;
    }
}