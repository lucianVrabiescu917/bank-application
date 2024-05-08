package com.example.bankapplication.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UnitEnum {
    @JsonProperty("kgs")
    KGS("kgs"),

    @JsonProperty("lbs")
    LBS("lbs"),

    @JsonProperty("tons")
    TONS("tons"),

    @JsonProperty("liters")
    LITERS("liters");


    private final String unit;

    UnitEnum(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return unit;
    }
}
