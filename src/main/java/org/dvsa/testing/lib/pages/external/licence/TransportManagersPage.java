package org.dvsa.testing.lib.pages.external.licence;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class TransportManagersPage extends BasePage {
    // Selectors
    private static String ADD_TRANSPORT_MANAGER_BUTTON = "//*/button[contains(text(), 'Add Transport Manager')]";

    private static String SAVE_AND_CONTINUE_BUTTON = "//*/button[contains(text(), 'Save and continue')]";
    private static String SAVE_AND_RETURN_TO_OVERVIEW_BUTTON = "//*/button[contains(text(), 'Save and return to overview')]";

    public static void saveAndContinue() throws UninitialisedDriverException {
        click(SAVE_AND_CONTINUE_BUTTON, SelectorType.XPATH);
    }

    public static void saveAndReturnToOverview() throws UninitialisedDriverException {
        click(SAVE_AND_RETURN_TO_OVERVIEW_BUTTON, SelectorType.XPATH);
    }
}
