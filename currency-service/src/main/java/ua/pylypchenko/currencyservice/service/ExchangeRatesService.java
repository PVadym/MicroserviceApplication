package ua.pylypchenko.currencyservice.service;

import ua.pylypchenko.currencyservice.domain.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface ExchangeRatesService {

    Map<Currency, BigDecimal> getRates();

    Map<Currency, BigDecimal> getRatesByCurrency(Currency currency);

    Map<String, String> getAllCurrencies();

    Map<Currency, BigDecimal> getRatesByDate(LocalDate date);

    BigDecimal convertMoney(Currency from, Currency to, BigDecimal amount);





}
