package com.demo.elearn.models.enums;

public enum Currency {
    USD("USD"),
    INR("INR");

    private String code;

    Currency(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public String getName() {
        return code;
    }

}