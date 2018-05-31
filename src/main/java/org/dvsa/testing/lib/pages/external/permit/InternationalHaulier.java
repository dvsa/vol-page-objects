package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.jetbrains.annotations.NotNull;

public class InternationalHaulier extends BasePage {

    private static String CABOTAGE_TEMPLATE = "#cabotage label:nth-of-type(%d) input";
    private static String EURO_SIX_COMPLIANT_VEHICLES = "#euro6 label:nth-of-type(%d) input";
    private static String COUNTRIES_TEMPLATE = "#country input:nth-of-type(%d)";

    private static String ADD_ANOTHER_COUNTRY_LINK = "//a[text() = 'Add another country']";

    private static String SUBMIT_BUTTON = "input[type='submit']";
    private static String CANCEL_BUTTON = "//a[contains(text(), 'Cancel')]";

    public static void cabotage(boolean carries) {
        int pos = carries ? 1 : 2;
        click(String.format(CABOTAGE_TEMPLATE, pos));
    }

    public static void euroSixCompliantVehicles(boolean isCompliant) {
        int pos = isCompliant ? 1 : 2;
        click(String.format(EURO_SIX_COMPLIANT_VEHICLES, pos));
    }

    public static void deliversTo(@NotNull String... countries) {
        for(int pos = 0; pos < countries.length; pos++) {
            enterField(String.format(COUNTRIES_TEMPLATE, pos + 1), countries[pos]);
            if (pos + 1 < countries.length)
                addAnotherCountry();
        }
    }

    private static void addAnotherCountry() {
        click(ADD_ANOTHER_COUNTRY_LINK, SelectorType.XPATH);
    }

    private static void saveAndContinue() {
        click(SUBMIT_BUTTON);
    }

    private static void cancel() {
        click(CANCEL_BUTTON);
    }

}
