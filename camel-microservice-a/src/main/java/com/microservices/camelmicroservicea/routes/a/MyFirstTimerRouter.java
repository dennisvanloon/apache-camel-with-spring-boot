package com.microservices.camelmicroservicea.routes.a;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Autowired
    GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    SimpleLoggingProcessingComponent simpleLoggingProcessingComponent;

    @Override
    public void configure() throws Exception {
        // timer
        // transformation
        // log
        from("timer:first-timer")
        .log("${body}")
        .transform().constant("Custom Message")
        .log("${body}")
        //beans with a return type actually change the body of the message (transformation)
        .bean(getCurrentTimeBean, "getCurrentTime")
        .log("${body}")
        //beans returning void do not change the body (processing)
        .bean(simpleLoggingProcessingComponent, "process")
        .log("${body}")
        // processors also only process (no transformation)
        .process(new SimpleLoggingProcessor())
        .to("log:first-timer");
    }
    
}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time is now " + LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessingComponent {

    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
    
    public void process(String message) {
        logger.info("Time for logger in SimpleLoggingProcessingComponent is now {}", LocalDateTime.now());
    }
}

class SimpleLoggingProcessor implements Processor {

    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());
    }
}