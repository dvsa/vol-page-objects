package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class DeclarationPage extends BasePage {

    private static String TERMS_AND_CONDITIONS_LINK = "//a[text() = 'terms and conditions']";
    private static String GUIDANCE_NOTES_LINK = "//a[contains(text(), 'Guidance notes')]";

    private static String SUBMIT_AND_PAY_BUTTON;
    private static String CANCEL_BUTTON;

    public static void termsAndConditions() {
        scrollAndClick(TERMS_AND_CONDITIONS_LINK, SelectorType.XPATH);
    }

    public static void guidanceNotes() {
        scrollAndClick(GUIDANCE_NOTES_LINK, SelectorType.XPATH);
    }

    public static void submitAndPay() {
        scrollAndClick(SUBMIT_AND_PAY_BUTTON);
    }

}
