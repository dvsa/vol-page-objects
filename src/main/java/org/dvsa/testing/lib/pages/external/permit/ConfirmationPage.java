package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;

public class ConfirmationPage extends BasePage {

    private static String CONTINUE;

    public static void continueButton() {
        scrollAndClick(CONTINUE);
    }

}
