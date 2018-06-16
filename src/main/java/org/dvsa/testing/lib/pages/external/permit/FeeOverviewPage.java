package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;

public class FeeOverviewPage extends BasePage {

    private static String SUBMIT_AND_PAY;
    private static String SAVE_AND_RETURN;

    public static void submitAndPay() {
        scrollAndClick(SUBMIT_AND_PAY);
    }

    public static void saveAndReturn() {
        scrollAndClick(SAVE_AND_RETURN);
    }

}
