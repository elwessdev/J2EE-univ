package com.example.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public <RouteLocatorBuilder> RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product_route", r -> r
                        .path("/api/products/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://product-service"))
                .route("order_route", r -> r
                        .path("/api/orders/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://order-service"))
                .route("product_route", r -> r
                        .path("/api/products/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver())))
                        .uri("lb://product-service"))
                .route("product_route", r -> r
                        .path("/api/products/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .circuitBreaker(config -> config
                                        .setName("productServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/products")))
                        .uri("lb://product-service"))
                .build();
    }
}