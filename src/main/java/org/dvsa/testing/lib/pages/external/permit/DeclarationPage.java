package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class DeclarationPage extends BasePage {

    private static String ACCEPT_AND_CONTINUE = "//a[text() = 'Submit and pay']";
    private static String SAVE_AND_RETURN = "//a[contains(text(), 'Cancel')]";

    public static void acceptAndContinue() {
        scrollAndClick(ACCEPT_AND_CONTINUE, SelectorType.XPATH);
    }

    public static void saveAndReturn() {
        scrollAndClick(SAVE_AND_RETURN);
    }

}
