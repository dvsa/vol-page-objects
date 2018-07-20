package org.dvsa.testing.lib.pages.external.licence;

import activesupport.MissingDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class ReviewAndSubmitPage extends BasePage {
    // Selectors
    private static String SUBMIT_TO_OPERATOR_BUTTON = "button[name='form-actions[submit]']";
    private static String CHANGE_YOUR_DETAILS_BUTTON = "//*/a[contains(text(), 'Change your details')]";

    public static void submitToOperator() throws MissingDriverException {
        click(SUBMIT_TO_OPERATOR_BUTTON);
    }

    public static void changeYourDetails() throws MissingDriverException {
        click(CHANGE_YOUR_DETAILS_BUTTON, SelectorType.XPATH);
    }
}
