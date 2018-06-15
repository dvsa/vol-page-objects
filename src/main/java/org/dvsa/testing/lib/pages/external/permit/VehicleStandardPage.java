package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;

public class VehicleStandardPage extends BasePage {

    private static String IS_EURO_6_COMPLIANT_TEMPLATE;

    private static String SAVE_AND_CONTINUE;
    private static String SAVE_AND_RETURN;

    public static void isEuro6Compliant(boolean isCompliant) {
        int pos = isCompliant ? 1 : 2;
        scrollAndClick(String.format(IS_EURO_6_COMPLIANT_TEMPLATE, pos));
    }

    public static void saveAndContinue() {
        scrollAndClick(SAVE_AND_CONTINUE);
    }

    public static void saveAndReturn() {
        scrollAndClick(SAVE_AND_RETURN);
    }

}
