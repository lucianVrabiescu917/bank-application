package com.example.bankapplication.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RolesEnum {
    @JsonProperty("EXPORTER")
    EXPORTER("EXPORTER"),

    @JsonProperty("IMPORTER")
    IMPORTER("IMPORTER"),

    @JsonProperty("BANK")
    BANK("BANK");


    private final String text;

    RolesEnum(String statusString) {
        this.text = statusString;
    }

    @Override
    public String toString() {
        return text;
    }
}
