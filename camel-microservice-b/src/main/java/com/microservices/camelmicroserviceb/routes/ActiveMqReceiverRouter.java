package com.microservices.camelmicroserviceb.routes;

import java.math.BigDecimal;

import com.microservices.camelmicroserviceb.CurrencyExchange;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqReceiverRouter extends RouteBuilder {

    @Autowired
    CurrencyExchangeLogger currencyExchangeLogger;

    @Autowired
    CurrencyExchangeTransformer currencyExchangeTransformer;

    @Override
    public void configure() throws Exception {
        from("activemq:my-activemq-queue")
        .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
        .bean(currencyExchangeLogger)
        .bean(currencyExchangeTransformer)
        .bean(currencyExchangeLogger)
        .to("log:received-message-from-activemq");
    }
    
}

@Component
class CurrencyExchangeLogger {

    Logger logger = LoggerFactory.getLogger(CurrencyExchangeLogger.class);

    public void processMessage(CurrencyExchange currencyExchange) {
        logger.info("Processing currency exchange {}", currencyExchange);
    }
}

@Component
class CurrencyExchangeTransformer {

    Logger logger = LoggerFactory.getLogger(CurrencyExchangeTransformer.class);

    public CurrencyExchange processMessage(CurrencyExchange currencyExchange) {
        currencyExchange.setConversion(currencyExchange.getConversion().multiply(BigDecimal.TEN));
        return currencyExchange;
    }
}
