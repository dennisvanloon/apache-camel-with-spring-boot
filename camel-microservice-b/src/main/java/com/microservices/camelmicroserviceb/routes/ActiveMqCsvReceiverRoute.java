package com.microservices.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqCsvReceiverRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:split-queue")
        .to("log:received-csv-message-from-activemq");
    }
    
}
