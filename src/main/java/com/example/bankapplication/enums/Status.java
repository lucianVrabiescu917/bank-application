package com.example.bankapplication.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {

    @JsonProperty("ACCEPTED")
    ACCEPTED("ACCEPTED"),

    @JsonProperty("DECLINED")
    DECLINED("DECLINED"),

    @JsonProperty("DECLINED BANK")
    DECLINED_BANK("DECLINED BANK"),

    @JsonProperty("ACCEPTED BANK")
    ACCEPTED_BANK("ACCEPTED BANK"),

    @JsonProperty("PENDING")
    PENDING("PENDING");


    private final String statusString;

    Status(String statusString) {
        this.statusString = statusString;
    }

    @Override
    public String toString() {
        return statusString;
    }
}
