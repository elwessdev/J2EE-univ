package com.example.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String requestId = UUID.randomUUID().toString();
        logger.info("Request ID: {}, Method: {}, Path: {}, Source IP: {}",
                requestId,
                request.getMethod(),
                request.getPath(),
                request.getRemoteAddress());

        long startTime = System.currentTimeMillis();

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            long endTime = System.currentTimeMillis();
            logger.info("Request ID: {}, Response Status: {}, Duration: {} ms",
                    requestId,
                    exchange.getResponse().getStatusCode(),
                    (endTime - startTime));
        }));
    }

    @Override
    public int getOrder() {
        return -1; // High priority to ensure this runs first
    }
}