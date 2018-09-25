package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.permit.enums.ApplicationInfo;
import org.jetbrains.annotations.NotNull;

public class CheckYourAnswersPage extends BasePermitPage {
    private static String ANSWER_SECTION = "//h4[contains(text(), '%s')]/../../";
    private static String ANSWER_SECTION_CHANGE = ANSWER_SECTION + "td[3]/a";
    private static String ANSWER_SECTION_ANSWER = ANSWER_SECTION + "td[2]";

    private static String ANSWER_ROW_TEMPLATE = "tbody tr:nth-of-type(%d) ";

    private static String ANSWER_VALUE_DATA_CELL = " td:nth-of-type(2)";

    private static String CONFIRM_AND_CONTINUE_BUTTON = "#submit-accept-button";

    public static String getAnswer(@NotNull ApplicationInfo info) {
        String selector = String.format(ANSWER_SECTION_ANSWER, info.toString());

        scrollTo(selector, SelectorType.XPATH, BasePage.WAIT_TIME_SECONDS);

        return getText(selector, SelectorType.XPATH);
    }

    public static void edit(@NotNull ApplicationInfo section) {
        String selector = String.format(ANSWER_SECTION_CHANGE, section.toString());

        scrollAndClick(selector, SelectorType.XPATH);
    }

    public static void saveAndContinue() {
        scrollAndClick(CONFIRM_AND_CONTINUE_BUTTON);
    }

}
