package com.example.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // 커스텀 프리필터
        return (exchange, chain) -> {
            ServerHttpRequest serverRequest = exchange.getRequest();
            ServerHttpResponse serverResponse = exchange.getResponse();

            log.info("커스텀 프리필터 : 리퀘스트 ID -> {} ", serverRequest.getId());

            // 커스텀 포스트필터
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("커스텀 포스트필터 : 리스폰스 상태코드 -> {}", serverResponse.getStatusCode());
            }));
        };
    }

    public static class Config {

    }
}
