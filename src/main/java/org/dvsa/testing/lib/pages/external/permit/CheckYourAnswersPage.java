package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.permit.enums.ApplicationInfo;
import org.jetbrains.annotations.NotNull;

public class CheckYourAnswersPage extends BasePermitPage {
    private static String ANSWER_ROW_TEMPLATE = "tbody tr:nth-of-type(%d) ";

    private static String ANSWER_VALUE_DATA_CELL = " td:nth-of-type(2)";

    private static String CONFIRM_AND_CONTINUE_BUTTON = "#submit-accept-button";

    public static String getAnswer(@NotNull ApplicationInfo info) {
        String selector = String.format(ANSWER_ROW_TEMPLATE, info.ordinal() + 1);

        scrollTo(selector, SelectorType.CSS, BasePage.WAIT_TIME_SECONDS);

        return getText(selector + ANSWER_VALUE_DATA_CELL, SelectorType.CSS);
    }

    public static void saveAndContinue() {
        scrollAndClick(CONFIRM_AND_CONTINUE_BUTTON);
    }

}
