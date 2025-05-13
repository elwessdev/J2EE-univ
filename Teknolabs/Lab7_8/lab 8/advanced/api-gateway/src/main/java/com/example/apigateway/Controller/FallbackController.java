package com.example.apigateway.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/books")
    public Mono<Map<String, String>> bookServiceFallback() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", "Book Service is currently unavailable. Please try again later.");
        return Mono.just(response);
    }

    @GetMapping("/users")
    public Mono<Map<String, String>> userServiceFallback() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", "User Service is currently unavailable. Please try again later.");
        return Mono.just(response);
    }

    @GetMapping("/analytics")
    public Mono<Map<String, String>> analyticsServiceFallback() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", "Analytics Service is currently unavailable. Please try again later.");
        return Mono.just(response);
    }
}