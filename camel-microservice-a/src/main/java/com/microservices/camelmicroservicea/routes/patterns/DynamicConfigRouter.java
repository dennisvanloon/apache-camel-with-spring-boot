package com.microservices.camelmicroservicea.routes.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DynamicConfigRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:timer?period={{timePeriod}}")
        .to("{{endpoint-for-logging}}");
        
    }
    
}
