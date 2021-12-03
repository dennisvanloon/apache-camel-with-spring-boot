package com.microservices.camelmicroservicea.routes.patterns;

import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class SplitPatternRouter extends RouteBuilder {

    @Autowired
    SplitterComponent splitter;

    @Override
    public void configure() throws Exception {
        from("file:files/csv")
//        .unmarshal().csv()
//        .split(body())
        .split(method(splitter))
        .to("activemq:split-queue");
    }
    
}

//@Component
class SplitterComponent {
    public List<String> split(String body) {
        return List.of("ABC", "CDE", "DEF");
    }
}