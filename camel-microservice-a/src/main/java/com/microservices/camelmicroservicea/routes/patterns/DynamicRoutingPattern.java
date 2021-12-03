package com.microservices.camelmicroservicea.routes.patterns;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DynamicRoutingPattern extends RouteBuilder {

    @Autowired
    DynamicRouterBean dynamicRouterBean;

    @Override
    public void configure() throws Exception {
        from("timer:dynamicRouting?period=5000")
        .transform().constant("My message is hardcoded")
        .dynamicRouter(method(dynamicRouterBean));

        from("direct:endpoint-1")
        .log("log:direct-endpoint-1");

        from("direct:endpoint-2")
        .log("log:direct-endpoint-2");

        from("direct:endpoint-3")
        .log("log:direct-endpoint-3");
    }
    
}

@Component
class DynamicRouterBean {

    Logger logger = LoggerFactory.getLogger(DynamicRouterBean.class);

    int invocations;

    public String decideNextEndpoint(@ExchangeProperties Map<String, String> properties,
                       @Headers Map<String, String> headers,
                       @Body String body) {
        
        logger.info("{}, {}, {}", properties, headers, body);
        invocations++;

        if (invocations%3==0) {
            return "direct:endpoint-1";
        }

        if (invocations%3==1) {
            return "direct:endpoint-1,direct:endpoint-2,direct:endpoint-3";
        }

        return null;

    }             
    
}