package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;

public class DeclarationPage extends BasePage {

    private static String ACCEPT_AND_CONTINUE = "#submitbutton";
    private static String SAVE_AND_RETURN = "//a[contains(text(), 'Save and return to permits')]";

    public static void acceptAndContinue() {
        scrollAndClick(ACCEPT_AND_CONTINUE);
    }

    public static void saveAndReturn() {
        scrollAndClick(SAVE_AND_RETURN);
    }

}
