package org.dvsa.testing.lib.pages.external;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.enums.external.home.Tab;
import org.dvsa.testing.lib.pages.exception.ElementDidNotAppearWithinSpecifiedTimeException;

public class HomePage extends BasePage {
    // Selectors
    private static String APPLY_FOR_LICENCE_BUTTON = "//*/a[contains(text(), 'Apply for a licence')]";
    private static String APPLY = "//a[contains(text(), 'Apply for a')]";

    private static String TAB_TEMPLATE = "//*/ul[@class='tab-list']//a[contains(text(), '%s')]";

    private static class PermitsTab {
        // Attributes
        private String RESOURCE_PATH = "permits/";

        public void apply() {
            click(APPLY);
        }
    }

    // Attributes
    private static String PAGE_TITLE_TEXT = "Home";
    private static String RESOURCE_PATH = "dashboard/";

    // Behaviour
    public static void selectTab(Tab tab) {
        click(String.format(TAB_TEMPLATE, tab.toString()), SelectorType.XPATH);
    }

    public static void applyForLicenceButton() throws UninitialisedDriverException {
        click(APPLY_FOR_LICENCE_BUTTON, SelectorType.XPATH);
    }

    public static void untilOnPage() throws UninitialisedDriverException, ElementDidNotAppearWithinSpecifiedTimeException {
        untilElementPresent(APPLY_FOR_LICENCE_BUTTON, SelectorType.XPATH);
    }

}
