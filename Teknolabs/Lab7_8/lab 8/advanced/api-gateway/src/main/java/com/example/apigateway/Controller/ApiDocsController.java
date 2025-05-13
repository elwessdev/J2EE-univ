package com.example.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApiDocsController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/api-docs")
    public Mono<Map<String, Object>> getApiDocs() {
        List<String> services = discoveryClient.getServices();
        List<Map<String, String>> serviceUrls = new ArrayList<>();

        for (String service : services) {
            if (!service.equals("api-gateway")) {
                Map<String, String> serviceUrl = new HashMap<>();
                serviceUrl.put("name", service);
                serviceUrl.put("url", "/" + service + "/v3/api-docs");
                serviceUrls.add(serviceUrl);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("services", serviceUrls);
        return Mono.just(response);
    }
}