package com.demo.elearn.models.enums;

public enum ValueType {
    VALUE("VALUE"),
    PERCENTAGE("PERCENTAGE");

    private String value;

    ValueType(String value) {
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