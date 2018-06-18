package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.permit.enums.ApplicationInfo;
import org.jetbrains.annotations.NotNull;

public class ApplicationOverviewPage extends BasePage {
    // TODO: fill in identifiers once devs complete pages
    private static String ANSWER_ROW_TEMPLATE = "tbody tr:nth-of-type(%d) ";

    private static String ANSWER_VALUE_DATA_CELL = " td:nth-of-type(2)";

    private static String ACCEPT_AND_CONTINUE_BUTTON = "//a[contains(text(), 'Accept and continue')]";
    private static String SAVE_AND_RETURN = "//a[contains(text(), 'Save and return to permits')]";

    public static String getAnswer(@NotNull ApplicationInfo info) {
        String selector = String.format(ANSWER_ROW_TEMPLATE, info.ordinal() + 1);

        scrollTo(selector, SelectorType.CSS, BasePage.WAIT_TIME_SECONDS);

        return getText(selector + ANSWER_VALUE_DATA_CELL, SelectorType.CSS);
    }

    public static void acceptAndContinue() {
        scrollAndClick(ACCEPT_AND_CONTINUE_BUTTON, SelectorType.XPATH);
    }

    public static void SaveAndReturn() {
        scrollAndClick(SAVE_AND_RETURN);
    }

}
