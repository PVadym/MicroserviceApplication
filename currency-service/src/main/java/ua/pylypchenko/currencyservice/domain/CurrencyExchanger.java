package ua.pylypchenko.currencyservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyExchanger {

    private ResponseExchange query;

    private BigDecimal result;

    public ResponseExchange getQuery() {
        return query;
    }

    public void setQuery(ResponseExchange query) {
        this.query = query;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CurrencyExchanger{" +
                "query=" + query +
                ", result=" + result +
                '}';
    }
}
