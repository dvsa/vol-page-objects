package org.dvsa.testing.lib.pages.external.permit;

import org.jetbrains.annotations.NotNull;

public class NumberOfTripsPage extends BasePermitPage {

    private static String TRIPS_ABROAD = "#TripsAbroad";

    public static void quantity(@NotNull String quantity){
        scrollAndEnterField(TRIPS_ABROAD, quantity);
    }

    public static void quantity(int quantity){
        quantity(String.valueOf(quantity));
    }

}
