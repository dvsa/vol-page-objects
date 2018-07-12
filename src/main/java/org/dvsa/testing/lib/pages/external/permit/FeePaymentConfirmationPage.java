package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;

public class FeePaymentConfirmationPage extends BasePage {

    private static String MAKE_PAYMENT;
    private static String BACK;

    private static String CANCEL;

    public static void makeMayment() {
        scrollAndClick(MAKE_PAYMENT);
    }

    public static void back() {
        scrollAndClick(BACK);
    }

    public static void cancel() {
        scrollAndClick(CANCEL);
    }

}
