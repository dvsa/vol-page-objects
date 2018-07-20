package org.dvsa.testing.lib.pages.internal;

import activesupport.MissingDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.enums.dataretention.SystemParameter;
import org.dvsa.testing.lib.pages.exception.ElementDidNotAppearWithinSpecifiedTimeException;
import org.dvsa.testing.lib.pages.exception.ElementDidNotDisappearWithinSpecifiedTimeException;
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

        public static void parameterValue(@NotNull String systemParameter) throws MissingDriverException, ElementDidNotAppearWithinSpecifiedTimeException {
            untilElementPresentWithin(PARAMETER_VALUE, 3);
            enterField(PARAMETER_VALUE, systemParameter);
            save();
            untilNotExpectedTextInElement("h1", PAGE_TITLE_TEXT, 3);
        }

        public static void save() throws MissingDriverException {
            click(SAVE_BUTTON);
        }
    }

    // Behaviour
    public static void set(@NotNull SystemParameter systemParameter, @NotNull String value) throws MissingDriverException, ElementDidNotAppearWithinSpecifiedTimeException, UnableToFindSystemParameter, ElementDidNotDisappearWithinSpecifiedTimeException {
        paginateToSystemParameter(systemParameter);
        click(String.format(ROW_KEY_TEMPLATE, systemParameter.getName()), SelectorType.XPATH);
        EditModel.parameterValue(value);
    }

    private static void paginateToSystemParameter(@NotNull SystemParameter systemParameter) throws MissingDriverException, UnableToFindSystemParameter {
        String keyToPress = String.format(ROW_KEY_TEMPLATE, systemParameter.getName());

        while (!isElementPresent(keyToPress, SelectorType.XPATH) && isElementPresent(NEXT_BUTTON, SelectorType.XPATH)) {
            click(NEXT_BUTTON, SelectorType.XPATH);
        }

        if (isElementNotPresent(keyToPress, SelectorType.XPATH)) {
            throw new UnableToFindSystemParameter("[ERROR] Unable to find " + systemParameter.getName() + " system property through all paginated pages");
        }

    }

}
