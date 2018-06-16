package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;

public class FeeSaveCardDetailsPage extends BasePage {

    private static String SAVE;
    private static String CANCEL;
    private static String BACK;

    public static void save() {
        scrollAndClick(SAVE);
    }

    public static void cancel() {
        scrollAndClick(CANCEL);
    }

    public static void back() {
        scrollAndClick(BACK);
    }

}
