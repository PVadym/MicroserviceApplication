package ua.pylypchenko.currencyservice.domain;

public enum Currency {

    EUR, USD, UAH;

    public static Currency getBase() {
        return EUR;
    }
}
