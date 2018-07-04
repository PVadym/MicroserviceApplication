package ua.pylypchenko.currencyservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyInfo {

    private Map<String, String> symbols;

    public Map<String, String> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, String> symbols) {
        this.symbols = symbols;
    }

    @Override
    public String toString() {
        return "CurrencyInfo{" +
                "symbols=" + symbols +
                '}';
    }
}
