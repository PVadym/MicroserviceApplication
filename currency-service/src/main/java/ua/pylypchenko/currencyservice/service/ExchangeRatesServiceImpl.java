package ua.pylypchenko.currencyservice.service;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.pylypchenko.currencyservice.client.ExchangeRatesClient;
import ua.pylypchenko.currencyservice.domain.Currency;
import ua.pylypchenko.currencyservice.domain.CurrencyInfo;
import ua.pylypchenko.currencyservice.domain.ExchangeRatesContainer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@Slf4j
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    @Value("${rates.apiKey}")
    private String apiKey;

    @Autowired
    private ExchangeRatesClient client;


    @Override
    public Map<Currency, BigDecimal> getRates() {

        ExchangeRatesContainer container = client.getRates(apiKey, Currency.getBase().name());

        log.info("exchange rates have been updated: {}", container);


        return ImmutableMap.of(
                Currency.EUR, container.getRates().get(Currency.EUR.name()),
                Currency.USD, container.getRates().get(Currency.USD.name()),
                Currency.UAH, container.getRates().get(Currency.UAH.name())
        );
    }

    @Override
    public Map<Currency, BigDecimal> getRatesByCurrency(Currency currency) {
        ExchangeRatesContainer container = client.getRates(apiKey, currency.name());

        log.info("exchange rates have been updated: {}", container);


        return ImmutableMap.of(
                Currency.EUR, container.getRates().getOrDefault(Currency.EUR.name(), BigDecimal.ONE),
                Currency.USD, container.getRates().getOrDefault(Currency.USD.name(), BigDecimal.ONE),
                Currency.UAH, container.getRates().getOrDefault(Currency.UAH.name(), BigDecimal.ONE)
        );

    }

    @Override
    public Map<String, String> getAllCurrencies() {
        CurrencyInfo info = client.getAvailableCurrencies(apiKey);
        if(info!=null){
            return Maps.newHashMap(info.getSymbols());
        }
        return Maps.newHashMap();
    }

    @Override
    public Map<Currency, BigDecimal> getRatesByDate(LocalDate date) {
        ExchangeRatesContainer container = client.getRatesByDate(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                apiKey,
                Currency.getBase().name());

        log.info("exchange rates have been updated: {}", container);


        return ImmutableMap.of(
                Currency.EUR, container.getRates().get(Currency.EUR.name()),
                Currency.USD, container.getRates().get(Currency.USD.name()),
                Currency.UAH, container.getRates().get(Currency.UAH.name())
        );
    }

//    @Override
//    public BigDecimal convertMoney(Currency from, Currency to, BigDecimal amount) {
//
//        CurrencyExchanger exchanger = client.convert(apiKey,from.name(),to.name(),amount);
//
//        if(exchanger!=null){
//            log.info("exchange money from %s to %s amount %d", exchanger.getQuery().getFrom(), exchanger.getQuery().getTo(), exchanger.getQuery().getAmount());
//
//            return exchanger.getResult();
//        }
//        return BigDecimal.ZERO;
//    }


    @Override
    public BigDecimal convertMoney(Currency from, Currency to, BigDecimal amount) {
        Map <Currency, BigDecimal> currencyMap = this.getRates();
        BigDecimal ratesTo = currencyMap.get(to);
        BigDecimal result = amount.multiply(ratesTo);
        return result;
    }
}
