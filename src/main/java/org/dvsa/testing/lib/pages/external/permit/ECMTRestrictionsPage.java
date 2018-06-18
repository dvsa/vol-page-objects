package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class ECMTRestrictionsPage extends BasePage {
    // TODO: fill in identifiers once devs complete pages
    private static String ECMT_RESTRICTED_COUNTRY_TEMPLATE = "#restricted-countries label:nth-of-type(%d) input[type='radio']";

    private static String SAVE_AND_CONTINUE = "input[type='submit']";
    private static String SAVE_AND_RETURN = "//a[contains(text(), 'Cancel')]";

    public static void deliverToRestrictedCountry(boolean answer) {
        int position = answer ? 1 : 2;
        scrollAndClick(String.format(ECMT_RESTRICTED_COUNTRY_TEMPLATE, position));
    }

    public static void saveAndContinue() {
        scrollAndClick(SAVE_AND_CONTINUE);
    }

    public static void saveAndReturn() {
        scrollAndClick(SAVE_AND_RETURN, SelectorType.XPATH);
    }

}
