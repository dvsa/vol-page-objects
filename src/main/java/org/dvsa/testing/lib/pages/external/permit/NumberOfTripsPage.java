package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class NumberOfTripsPage extends BasePage {
    private static String NUMBER_OF_TRIPS_FIELD = "#numberOfTrips";

    private static String SAVE_AND_CONTINUE_BUTTON = "input[type='submit']";
    private static String CANCEL_BUTTON = "//a[contains(text(), 'Cancel')]";

    public static void numberOfTrips(int numberOfTrips) {
        enterField(NUMBER_OF_TRIPS_FIELD, String.valueOf(numberOfTrips));
    }

    public static void saveAndContinue() {
        click(SAVE_AND_CONTINUE_BUTTON);
    }

    public static void cancel() {
        click(CANCEL_BUTTON, SelectorType.XPATH);
    }

}
