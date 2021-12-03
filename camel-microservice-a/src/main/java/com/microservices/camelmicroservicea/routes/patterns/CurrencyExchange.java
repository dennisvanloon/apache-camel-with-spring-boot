package com.microservices.camelmicroservicea.routes.patterns;

import java.math.BigDecimal;

public class CurrencyExchange {

    private Long id;
    private String from;
    private String to;
    private BigDecimal conversion;

    public CurrencyExchange() {
    }

    public CurrencyExchange(Long id, String from, String to, BigDecimal conversion) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversion = conversion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getConversion() {
        return conversion;
    }

    public void setConversion(BigDecimal conversion) {
        this.conversion = conversion;
    }

    @Override
    public String toString() {
        return "CurrencyExchange [conversion=" + conversion + ", from=" + from + ", id=" + id + ", to=" + to + "]";
    }

}
