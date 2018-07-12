package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.jetbrains.annotations.NotNull;

public class FeeCardholderDetailsPage extends BasePage {

    private static String CARD_HOLDERS_NAME;

    private static String ADDRESS_TEMPLATE;

    private static String COUNTY;
    private static String COUNTRY;

    private static String POST_CODE;

    private static String EMAIL;
    private static String EMAIL_CONFIRMATION;

    private static String CONTINUE;
    private static String BACK;
    private static String RESET;

    public static void cardHoldersName(@NotNull String name) {
        scrollAndEnterField(CARD_HOLDERS_NAME, name);
    }

    public static void address(String addressLine1, String addressLine2, String addressLine3) {
        scrollAndEnterField(String.format(ADDRESS_TEMPLATE, 1), addressLine1);
        scrollAndEnterField(String.format(ADDRESS_TEMPLATE, 2), addressLine2);
        scrollAndEnterField(String.format(ADDRESS_TEMPLATE, 3), addressLine3);
    }

    public static void county(String county) {
        scrollAndEnterField(COUNTY, county);
    }

    public static void country(String country) {
        scrollAndEnterField(COUNTRY, country);
    }

    public static void postCode(String postCode) {
        scrollAndEnterField(POST_CODE, postCode);
    }

    public static void email(String email) {
        scrollAndEnterField(EMAIL, email);
    }

    public static void emailConfirmation(String emailConfirmation) {
        scrollAndEnterField(EMAIL_CONFIRMATION, emailConfirmation);
    }

    public static void continueButton() {
        scrollAndClick(CONTINUE);
    }

    public static void back() {
        scrollAndClick(BACK);
    }

    public static void reset() {
        scrollAndClick(RESET);
    }

}
