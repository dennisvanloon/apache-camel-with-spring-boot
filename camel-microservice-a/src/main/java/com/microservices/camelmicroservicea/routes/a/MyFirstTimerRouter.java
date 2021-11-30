package com.microservices.camelmicroservicea.routes.a;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // timer
        // transformation
        // log
        from("timer:first-timer")
        .transform().constant("Time is now " + LocalDateTime.now())
        .to("log:first-timer");
    }
    
}
