package org.dvsa.testing.lib.pages.external.licence;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class AwaitingOperatorReviewPage extends BasePage {
    // Selectors
    private static String CHANGE_YOUR_DETAILS = "//*/a[contains(text(), 'change your details')]";
    private static String BACK_TO_TRANSPORT_MANAGERS = "//*/a[contains(text(),'Back to Transport Manager')]";

    // Behaviour
    public static void changeYourDetails() throws UninitialisedDriverException {
        click(CHANGE_YOUR_DETAILS, SelectorType.XPATH);
    }

    public static void backToTransportManagers() throws UninitialisedDriverException {
        click(BACK_TO_TRANSPORT_MANAGERS, SelectorType.XPATH);
    }
}
