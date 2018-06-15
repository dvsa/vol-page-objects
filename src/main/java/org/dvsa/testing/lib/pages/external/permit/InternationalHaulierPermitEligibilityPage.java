package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class InternationalHaulierPermitEligibilityPage extends BasePage {

    private static String CONTINUE_BUTTON = "//a[text() = 'Continue']";
    private static String CANCEL_BUTTON = "//a[contains(text(), 'Cancel')]";

    public static void continueButton() {
        click(CONTINUE_BUTTON, SelectorType.XPATH);
    }

    public static void cancel() {
        click(CANCEL_BUTTON, SelectorType.XPATH);
    }
}
