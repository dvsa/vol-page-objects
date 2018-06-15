package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class SubmittedPage extends BasePage {
    private static String REFERENCE_NUMBER = "//p[contains(text(), 'Your reference number is')]/strong";

    private static String FINISH_BUTTON = "//a[contains(text(), 'Finish')]";

    public static String referenceNumber() {
        return getText(REFERENCE_NUMBER, SelectorType.XPATH);
    }

    public static void finish() {
        scrollAndClick(FINISH_BUTTON, SelectorType.XPATH);
    }

}
