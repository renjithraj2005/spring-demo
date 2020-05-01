package com.demo.elearn.models.enums;

public enum PriceComponentType {
    TAX("Tax"),
    CURRENCY_CONVERSION("Currency Conversion"),
    OTHER("Other");

    private String description;

    PriceComponentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
