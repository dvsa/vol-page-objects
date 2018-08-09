package org.dvsa.testing.lib.pages.external;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.enums.external.home.PermitMessage;
import org.dvsa.testing.lib.pages.enums.external.home.Tab;
import org.dvsa.testing.lib.pages.exception.ElementDidNotAppearWithinSpecifiedTimeException;
import org.dvsa.testing.lib.pages.exception.FoundElementException;

import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {
    // Selectors
    private static String APPLY_FOR_LICENCE_BUTTON = "//*/a[contains(text(), 'Apply for a permit')]";
    private static String APPLY = "//a[contains(text(), 'Apply for a')]";

    private static String TAB_TEMPLATE = "//*/ul[@class='tab-list']//a[contains(text(), '%s')]";

    public static class PermitsTab {
        // Attributes
        final public static String RESOURCE = "/permits";

        public void apply() {
            click(APPLY);
        }

        public static void isPermitMessagePresent(PermitMessage permitMessage){
            if (!BasePage.isElementPresent(String.format("//*[]contains(text(),'%s')", permitMessage.toString()))){
                throw new FoundElementException("Permit message '" + permitMessage.toString() + "' should be present");
            }
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
        scrollAndClick(APPLY_FOR_LICENCE_BUTTON, SelectorType.XPATH);
    }

    // Validations
    public static void untilOnPage() throws UninitialisedDriverException, ElementDidNotAppearWithinSpecifiedTimeException {
        untilElementPresent(APPLY_FOR_LICENCE_BUTTON, SelectorType.XPATH);
    }

    public static void tabIsPresent(Tab tab){
        String selector = String.format(TAB_TEMPLATE, tab.toString());
        untilElementIsPresent(selector, SelectorType.XPATH, 30, TimeUnit.SECONDS);
    }

    public static void tabIsNotPresent(Tab tab){
        String selector = String.format(TAB_TEMPLATE, tab.toString());
        elementIsNotPresent(selector, SelectorType.XPATH);
    }

}
