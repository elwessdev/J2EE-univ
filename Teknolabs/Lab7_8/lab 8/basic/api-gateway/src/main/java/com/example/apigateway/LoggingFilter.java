package com.example.apigateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class LoggingFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Path requested: " + exchange.getRequest().getPath());

        // Add a request header
        return chain.filter(
                exchange.mutate()
                        .request(
                                exchange.getRequest()
                                        .mutate()
                                        .header("X-Request-Time", LocalDateTime.now().toString())
                                        .build()
                        )
                        .build()
        );
    }
}