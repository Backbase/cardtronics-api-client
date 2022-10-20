package com.backbase.accelerators.cardtronics.model;

import lombok.Getter;

@Getter
public enum LocationType {

    BRANCH("BRANCH"),
    ATM("ATM"),
    ALL("ALL");

    private final String value;

    LocationType(String value) {
        this.value = value;
    }
}
