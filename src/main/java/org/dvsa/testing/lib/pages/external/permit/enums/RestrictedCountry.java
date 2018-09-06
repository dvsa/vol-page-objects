package org.dvsa.testing.lib.pages.external.permit.enums;

import activesupport.number.Int;

public enum RestrictedCountry {

    Austria("Austria"),
    Greece("Greece"),
    Hungary("Hungary"),
    Italy("Italy"),
    RussianFederation("Russian Federation");

    private String country;

    RestrictedCountry(String country){
        this.country = country;
    }

    public static RestrictedCountry random() {
        int index = Int.random(0, RestrictedCountry.values().length - 1);
        return RestrictedCountry.values()[index];
    }

    @Override
    public String toString() {
        return country;
    }

}
