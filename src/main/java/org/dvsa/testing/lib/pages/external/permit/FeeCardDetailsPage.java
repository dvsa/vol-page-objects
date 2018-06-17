package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;

public class FeeCardDetailsPage extends BasePage {

    private static String CARD_NUMBER_FIELD;
    private static String EXPIRY_DATE_TEMPLATE;
    private static String SECURITY_CODE;

    private static String CONTINUE;
    private static String RESET;
    private static String CANCEL;

    public static void cardNumber(int cardNumber) {
        scrollAndEnterField(CARD_NUMBER_FIELD, String.valueOf(cardNumber));
    }

    public static void cardNumber(String cardNumber) {
        cardNumber(Integer.parseInt(cardNumber));
    }

    public static void expiryDate(int month, int year) {
        scrollAndEnterField(String.format(EXPIRY_DATE_TEMPLATE, 1), String.valueOf(month));
        scrollAndEnterField(String.format(EXPIRY_DATE_TEMPLATE, 2), String.valueOf(year));
    }

    public static void securityCode(int securityCode) {
        scrollAndEnterField(SECURITY_CODE, String.valueOf(securityCode));
    }

    public static void continueButton() {
        scrollAndClick(CONTINUE);
    }

    public static void reset() {
        scrollAndClick(RESET);
    }

    public static void cancel() {
        scrollAndClick(CANCEL);
    }

}
