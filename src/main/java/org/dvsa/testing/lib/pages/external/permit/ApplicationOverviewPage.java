package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.jetbrains.annotations.NotNull;

public class ApplicationOverviewPage extends BasePage {
    // TODO: fill in identifiers once devs complete pages
    private static String ANSWER_ROW_TEMPLATE;

    private static String ANSWER_VALUE_DATA_CELL = " td:nth-of-type(2)";

    private static String ACCEPT_AND_CONTINUE_BUTTON;

    public static String getAnswer(@NotNull ApplicationInfo info) {
        String selector = String.format(ANSWER_ROW_TEMPLATE, info.ordinal() + 1);

        scrollTo(selector, SelectorType.CSS, BasePage.WAIT_TIME_SECONDS);

        return getText(selector + ANSWER_VALUE_DATA_CELL, SelectorType.CSS);
    }

    public static void acceptAndContinue() {
        click(ACCEPT_AND_CONTINUE_BUTTON);
    }

}
