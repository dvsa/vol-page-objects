package org.dvsa.testing.lib.pages.external.permit;

import org.jetbrains.annotations.NotNull;

public class NumberOfTripsPage extends BasePermitPage {

    private static String TRIPS_ABROAD = "#TripsAbroad";

    private static String INFO_FOR_NI = "Please exclude journeys to the republic of Ireland in your calculation of annual trips.";

    final public static String RESOURCE = "ecmt-trips/";

    private static String INTENSITY_MESSAGE = "You have stated a high intensity of use of these permits. Please check the details are correct. We may contact you to verify this information";

    public static void quantity(@NotNull String quantity){
        scrollAndEnterField(TRIPS_ABROAD, quantity);
    }

    public static void quantity(int quantity){
        quantity(String.valueOf(quantity));
    }

    public static boolean hasInfoForNIUsers(){
        return isTextPresent(INFO_FOR_NI);
    }

    public static boolean hasIntensityMessage() {
        return isTextPresent(INTENSITY_MESSAGE);
    }

}
