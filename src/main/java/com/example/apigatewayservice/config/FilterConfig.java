package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(r -> r.path("/first-service/**")
                        .filters(f -> f.addRequestHeader("f-req", "f-req-val")
                                .addResponseHeader("f-res", "f-res-val"))
                        .uri("http://localhost:8081"))
                .route(r -> r.path("/second-service/**")
                        .filters(f -> f.addRequestHeader("s-req", "s-req-val")
                                .addResponseHeader("s-res", "s-res-val"))
                        .uri("http://localhost:8082"))
                .build();
    }
}
