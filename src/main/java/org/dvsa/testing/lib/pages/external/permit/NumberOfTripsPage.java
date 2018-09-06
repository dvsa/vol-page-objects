package org.dvsa.testing.lib.pages.external.permit;

import org.jetbrains.annotations.NotNull;

public class NumberOfTripsPage extends BasePermitPage {

    private static String TRIPS_ABROAD = "#TripsAbroad";

    private static String INFO_FOR_NI = "Please exclude journeys to the republic of Ireland in your calculation of annual trips.";

    public static void quantity(@NotNull String quantity){
        scrollAndEnterField(TRIPS_ABROAD, quantity);
    }

    public static void quantity(int quantity){
        quantity(String.valueOf(quantity));
    }

    public static boolean hasInfoForNIUsers(){
        return isTextPresent(INFO_FOR_NI);
    }

}
