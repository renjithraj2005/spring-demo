package com.demo.elearn.models.enums;

public enum PriceStrategy {

    FREE("FREE"),
    ONETIME("ONETIME"),
    SUBSCRIPTION("SUBSCRIPTION");

    private String value;

    PriceStrategy(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getName() {
        return value;
    }

}