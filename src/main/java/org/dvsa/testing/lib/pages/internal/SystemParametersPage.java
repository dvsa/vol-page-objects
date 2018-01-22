package org.dvsa.testing.lib.pages.internal;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.enums.SystemParameter;
import org.dvsa.testing.lib.pages.exception.ElementDidNotAppearWithinSpecifiedTimeException;
import org.dvsa.testing.lib.pages.exception.IncorrectPageTitleException;
import org.dvsa.testing.lib.pages.exception.UnableToFindSystemParameter;
import org.jetbrains.annotations.NotNull;

public class SystemParametersPage extends BasePage {
    // Selectors
    private static String ROW_KEY_TEMPLATE = "//*/td/a[text()[contains(.,'%s')]]";
    private static String NEXT_BUTTON = "//*[@class='pagination__item']/a[text()[contains(.,'Next')]]";

    // Attributes
    private static String PAGE_TITLE_TEXT = "System parameters";

    private static class EditModel extends BasePage {
        // Selectors
        private static String PARAMETER_VALUE = nameAttribute("input", "system-parameter-details[paramValue]");
        private static String SAVE_BUTTON = nameAttribute("button", "form-actions[submit]");

        // Attributes
        private static String PAGE_TITLE_TEXT = "Edit system parameter";

        public static void parameterValue(@NotNull String systemParameter) throws UninitialisedDriverException, ElementDidNotAppearWithinSpecifiedTimeException, IncorrectPageTitleException {
            untilExpectedPageTitle(PAGE_TITLE_TEXT);
            untilElementPresentWithin(PARAMETER_VALUE, 1000);
            enterField(PARAMETER_VALUE, systemParameter);
            save();
            untilNotExpectedPageTitle(PAGE_TITLE_TEXT);
        }

        public static void save() throws UninitialisedDriverException {
            click(SAVE_BUTTON);
        }
    }

    // Behaviour
    public static void set(@NotNull SystemParameter systemParameter, @NotNull String value) throws UninitialisedDriverException, ElementDidNotAppearWithinSpecifiedTimeException, UnableToFindSystemParameter, IncorrectPageTitleException {
        paginateToSystemParameter(systemParameter);
        click(String.format(ROW_KEY_TEMPLATE, systemParameter.toString()), SelectorType.XPATH);
        EditModel.parameterValue(value);
    }

    private static void paginateToSystemParameter(@NotNull SystemParameter systemParameter) throws UninitialisedDriverException, UnableToFindSystemParameter {
        String keyToPress = String.format(ROW_KEY_TEMPLATE, systemParameter.toString());

        while (!isElementPresent(keyToPress, SelectorType.XPATH) && isElementPresent(NEXT_BUTTON, SelectorType.XPATH)) {
            click(NEXT_BUTTON, SelectorType.XPATH);
        }

        if (isElementPresent(keyToPress, SelectorType.XPATH)) {
            click(keyToPress, SelectorType.XPATH);
        } else {
            throw new UnableToFindSystemParameter("[ERROR] Unable to find " + systemParameter + " system property through all paginated pages");
        }

    }

    public static void untilExpectedPageTitle() throws IncorrectPageTitleException, UninitialisedDriverException {
        untilExpectedPageTitle(PAGE_TITLE_TEXT);
    }

    public static void untilExpectedPageTitle(long horizonMilliseconds) throws IncorrectPageTitleException, UninitialisedDriverException {
        untilExpectedPageTitle(PAGE_TITLE_TEXT, horizonMilliseconds);
    }

    public static boolean isExpectedPageTitle() throws UninitialisedDriverException {
        return BasePage.isExpectedPageTitle(PAGE_TITLE_TEXT);
    }

    public static boolean isExpectedPageTitle(long horizonMilliseconds) throws UninitialisedDriverException {
        return BasePage.isExpectedPageTitle(PAGE_TITLE_TEXT, horizonMilliseconds);
    }

    public static boolean isNotExpectedPageTile() throws UninitialisedDriverException {
        return BasePage.isNotExpectedPageTitle(PAGE_TITLE_TEXT);
    }

    public static boolean isNotExpectedPageTile(long horizonMilliseconds) throws UninitialisedDriverException {
        return BasePage.isNotExpectedPageTitle(PAGE_TITLE_TEXT, horizonMilliseconds);
    }

}
