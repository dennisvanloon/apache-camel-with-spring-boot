package com.microservices.camelmicroserviceb.routes;

import com.microservices.camelmicroserviceb.CurrencyExchange;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMqXmlReceiverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:my-activemq-xml-queue")
        .unmarshal()
        .jacksonxml(CurrencyExchange.class)
        .to("log:received-xml-message-from-activemq");
    }
    
}

