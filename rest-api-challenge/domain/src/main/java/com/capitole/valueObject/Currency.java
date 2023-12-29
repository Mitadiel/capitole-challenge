package com.capitole.valueObject;

import lombok.Getter;

@Getter
public enum Currency {
    USD("USD"),
    EUR("EUR"),
    GBP("GBP"),
    JPY("JPY");

    private final String isoCode;

    Currency(String isoCode) {
        this.isoCode = isoCode;
    }

}