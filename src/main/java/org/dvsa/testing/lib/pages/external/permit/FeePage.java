package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.permit.enums.CardType;

public class FeePage extends BasePage {
    // TODO: fill in identifiers once devs complete pages
    private static String PAYMENT_METHOD_TEMPLATE = "#payment-type label:nth-of-type(%d) input[type='radio']";

    private static String PAY_NOW_BUTTON = "//a[text()='Pay now']";

    public static void paymentMethod(CardType cardType) {
        scrollAndClick(String.format(PAYMENT_METHOD_TEMPLATE, cardType.ordinal() + 1), SelectorType.CSS);
    }

    public static void payNow() {
        scrollAndClick(PAY_NOW_BUTTON, SelectorType.XPATH);
    }

}
