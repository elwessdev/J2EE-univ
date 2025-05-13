package com.example.apigateway.filter;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Component
public class MetricsFilter implements GlobalFilter, Ordered {

    private final MeterRegistry meterRegistry;

    public MetricsFilter(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Instant start = Instant.now();
        String path = exchange.getRequest().getPath().value();
        String method = exchange.getRequest().getMethod().name();

        return chain.filter(exchange)
                .doFinally(signalType -> {
                    Duration duration = Duration.between(start, Instant.now());
                    String outcome = exchange.getResponse().getStatusCode() != null ?
                            exchange.getResponse().getStatusCode().toString() : "Unknown";

                    meterRegistry.timer("gateway.requests",
                                    "path", path,
                                    "method", method,
                                    "outcome", outcome)
                            .record(duration);

                    meterRegistry.counter("gateway.requests.count",
                                    "path", path,
                                    "method", method,
                                    "outcome", outcome)
                            .increment();
                });
    }

    @Override
    public int getOrder() {
        return -2; // Run before LoggingFilter but after security filters
    }
}