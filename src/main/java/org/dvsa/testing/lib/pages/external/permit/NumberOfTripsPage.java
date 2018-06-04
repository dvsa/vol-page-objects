package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;

public class NumberOfTripsPage extends BasePage {
    // TODO: fill in identifiers once devs complete pages
    private static String NUMBER_OF_TRIPS_FIELD;

    private static String SAVE_AND_CONTINUE_BUTTON;
    private static String CANCEL_BUTTON;

    public static void numberOfTrips(int numberOfTrips) {
        enterField(NUMBER_OF_TRIPS_FIELD, String.valueOf(numberOfTrips));
    }

    public static void saveAndContinue() {
        click(SAVE_AND_CONTINUE_BUTTON);
    }

    public static void cancel() {
        click(CANCEL_BUTTON);
    }

}
