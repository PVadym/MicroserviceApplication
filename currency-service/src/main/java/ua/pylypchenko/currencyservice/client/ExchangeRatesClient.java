package ua.pylypchenko.currencyservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.pylypchenko.currencyservice.domain.CurrencyExchanger;
import ua.pylypchenko.currencyservice.domain.CurrencyInfo;
import ua.pylypchenko.currencyservice.domain.ExchangeRatesContainer;

import java.math.BigDecimal;

@FeignClient(url = "${rates.url}", name = "rates-client")
public interface ExchangeRatesClient {

    @RequestMapping(method = RequestMethod.GET,value = "/latest")
    ExchangeRatesContainer getRates(@RequestParam(value = "access_key") String apiKey, @RequestParam("base") String base);

    @RequestMapping(method = RequestMethod.GET,value = "/symbols")
    CurrencyInfo getAvailableCurrencies(@RequestParam(value = "access_key") String apiKey);

    @RequestMapping(method = RequestMethod.GET,value = "/{date}")
    ExchangeRatesContainer getRatesByDate(@PathVariable("date") String date,
                                          @RequestParam(value = "access_key") String apiKey,
                                          @RequestParam("base") String base);

    @RequestMapping(method = RequestMethod.GET,value = "/convert")
    CurrencyExchanger convert(@RequestParam(value = "access_key") String apiKey,
                              @RequestParam(value = "from") String currencyFrom,
                              @RequestParam(value = "to") String currencyTo,
                              @RequestParam("amount") BigDecimal amount);




}