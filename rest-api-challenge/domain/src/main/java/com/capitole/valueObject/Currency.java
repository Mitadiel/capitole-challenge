package com.capitole.valueObject;

public enum Currency {
    USD("USD"),
    EUR("EUR"),
    GBP("GBP"),
    JPY("JPY");

    private final String isoCode;

    Currency(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getIsoCode() {
        return isoCode;
    }
}