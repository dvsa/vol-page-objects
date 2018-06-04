package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;

public class SubmittedPage extends BasePage {
    // TODO: fill in identifiers once devs complete pages
    private static String REFERENCE_NUMBER;

    private static String FINISH_BUTTON;

    public static String referenceNumber() {
        return getText(REFERENCE_NUMBER);
    }

    public static void finish() {
        scrollAndClick(FINISH_BUTTON);
    }

}
