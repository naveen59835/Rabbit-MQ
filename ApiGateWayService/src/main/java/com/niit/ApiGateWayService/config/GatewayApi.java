package com.niit.ApiGateWayService.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayApi {
@Bean
    public RouteLocator getRoutes(RouteLocatorBuilder builder){

        return builder.routes().route(p->p.path("/api/userService/**")
                .uri("lb://User-Server")).route(p->p.path("/api/trackService/**")
                .uri("lb://Track-Server")).build();
    }
}
