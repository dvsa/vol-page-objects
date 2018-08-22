package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;

public class ConfirmationPage extends BasePage {

    private static String CONTINUE;

    final public static String RESOURCE = "permits/114/ecmt-submitted";

    public static void continueButton() {
        scrollAndClick(CONTINUE);
    }

}
