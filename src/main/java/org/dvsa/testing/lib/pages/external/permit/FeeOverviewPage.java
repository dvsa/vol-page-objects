package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class FeeOverviewPage extends BasePage {

    private static String SUBMIT_AND_PAY = "//a[text()='Submit and pay']";
    private static String SAVE_AND_RETURN = "//a[contains(text(), 'Save and return to permits')]" ;

    public static void submitAndPay() {
        scrollAndClick(SUBMIT_AND_PAY, SelectorType.XPATH);
    }

    public static void saveAndReturn() {
        scrollAndClick(SAVE_AND_RETURN, SelectorType.XPATH);
    }

}
