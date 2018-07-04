package ua.pylypchenko.currencyservice.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.pylypchenko.currencyservice.domain.Currency;
import ua.pylypchenko.currencyservice.service.ExchangeRatesService;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

@RestController
@Slf4j
public class ExchangeRatesController {

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @RequestMapping("/rates")
    public ResponseEntity getCurrencyRates(@RequestParam(value = "currency", required = false) String currency){
        Map<Currency, BigDecimal> rates;

        if(StringUtils.isBlank(currency) || StringUtils.isEmpty(currency)){

            rates = exchangeRatesService.getRates();
        } else {
            Currency currencyFromRequest;
            try {
                currencyFromRequest = Currency.valueOf(currency);
            } catch (IllegalArgumentException e){
                log.info("Currency " + currency + " is not available!");
                return ResponseEntity.badRequest().build();
            }

            rates = exchangeRatesService.getRatesByCurrency(currencyFromRequest);
        }
        log.info("Rates have received, in class = " + ExchangeRatesController.class.getSimpleName());

        return ResponseEntity.ok(rates);
    }

    @RequestMapping("/currencies")
    public ResponseEntity getAllCurrencies() {

        Map<String, String> currencies = exchangeRatesService.getAllCurrencies();
        log.info("Get all currencies from API");
        return ResponseEntity.ok(currencies);
    }

    @RequestMapping("/rates/{date}")
    public ResponseEntity getRatesByDate(@PathVariable String date){
        if(StringUtils.isNotBlank(date) || StringUtils.isNotEmpty(date)){
            try {
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return ResponseEntity.ok(exchangeRatesService.getRatesByDate(localDate));
            } catch (DateTimeParseException e){
                log.info("Date " + date + " can not be parsed!");
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @RequestMapping("/convert/{to}/{amount}")
    public ResponseEntity convertAmount(
//                                        @PathVariable(value = "from") String currencyFrom,
                                        @PathVariable(value = "to") String currencyTo,
                                        @PathVariable(value = "amount") String amount){

        Currency from;
        Currency to;
        BigDecimal amountToExchange;
        try {
//            from = Currency.valueOf(currencyFrom);
            from = Currency.EUR;
            to = Currency.valueOf(currencyTo);
            amountToExchange = getNumberFromString(amount);
        } catch (IllegalArgumentException e){
            log.info("Currency exchange is not available!");
            return ResponseEntity.badRequest().build();
        } catch (ParseException e) {
            log.info("Amount can not be parse!");
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(exchangeRatesService.convertMoney(from,to,amountToExchange));

    }

    private BigDecimal getNumberFromString  (String amount) throws ParseException{
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance();

        Number amountToChange = decimalFormat.parse(amount);

        return new BigDecimal(amountToChange.doubleValue());



    }


}
