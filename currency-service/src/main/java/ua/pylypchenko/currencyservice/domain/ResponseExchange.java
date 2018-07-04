package ua.pylypchenko.currencyservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseExchange {

    private Currency from;

    private Currency to;

    private BigDecimal amount;

    public Currency getFrom() {
        return from;
    }

    public void setFrom(Currency from) {
        this.from = from;
    }

    public Currency getTo() {
        return to;
    }

    public void setTo(Currency to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ResponseExchange{" +
                "from=" + from +
                ", to=" + to +
                ", amount=" + amount +
                '}';
    }
}
