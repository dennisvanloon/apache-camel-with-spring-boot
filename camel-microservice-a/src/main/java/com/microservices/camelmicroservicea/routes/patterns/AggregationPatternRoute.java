package com.microservices.camelmicroservicea.routes.patterns;

import java.util.ArrayList;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class AggregationPatternRoute extends RouteBuilder {

    class ArrayListAggregationStrategy implements AggregationStrategy {

        public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
            Object newBody = newExchange.getIn().getBody();
            ArrayList<Object> list = null;
            if (oldExchange == null) {
                list = new ArrayList<Object>();
                list.add(newBody);
                newExchange.getIn().setBody(list);
                return newExchange;
            } else {
                list = oldExchange.getIn().getBody(ArrayList.class);
                list.add(newBody);
                return oldExchange;
            }
        }
    }

    @Override
    public void configure() throws Exception {
        from("file:files/aggregate-json")
        .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
        .aggregate(simple("${body.to}"), new ArrayListAggregationStrategy())
        .completionSize(3)
        .completionTimeout(1000)
        .to("log:aggregate-json");
    }
    
}
