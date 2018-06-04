package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;

public class ECMTRestrictionsPage extends BasePage {
    // TODO: fill in identifiers once devs complete pages
    private static String ECMT_RESTRICTED_COUNTRY_TEMPLATE;

    private static String SAVE_AND_CONTINUE_BUTTON;
    private static String CANCEL_BUTTON;

    public static void deliverToRestrictedCountry(boolean answer) {
        int position = answer ? 1 : 2;
        click(String.format(ECMT_RESTRICTED_COUNTRY_TEMPLATE, position));
    }

    public static void saveAndContinue() {
        click(SAVE_AND_CONTINUE_BUTTON);
    }

    public static void cancel() {
        click(CANCEL_BUTTON);
    }

}
