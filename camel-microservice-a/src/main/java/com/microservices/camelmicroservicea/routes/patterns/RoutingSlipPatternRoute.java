package com.microservices.camelmicroservicea.routes.patterns;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class RoutingSlipPatternRoute extends RouteBuilder {

    @Autowired
    MessageRouter messageRouter;

    @Override
    public void configure() throws Exception {
       
//        String routingSlip = "direct:endpoint-1,direct:endpoint-2,direct:endpoint-3";

        from("timer:routingSlip?period=1100")
        .transform().constant("My message is hardcoded")
//        .routingSlip(simple(routingSlip));
        .routingSlip(method(messageRouter));

        from("direct:endpoint-1")
        .log("log:direct-endpoint-1");
        
        from("direct:endpoint-2")
        .log("log:direct-endpoint-2");

        from("direct:endpoint-3")
        .log("log:direct-endpoint-3");
    }
    
}

@Component
class MessageRouter {
    
    private int second;

    public String deletermineSlip() {
        second = LocalDateTime.now().getSecond();
        if(second < 20 ) {
            return "direct:endpoint-1";
        } else if (second < 40) {
            return "direct:endpoint-2";
        }
        return "direct:endpoint-3";
    } 
}
