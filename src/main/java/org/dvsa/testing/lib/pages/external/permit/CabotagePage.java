package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;

public class CabotagePage extends BasePage {

    private static String CARRIES_CABOTAGE;

    private static String SAVE_AND_CONTINUE;
    private static String SAVE_AND_RETURN;

    public static void carryCabotage(boolean cabotage) {
        int pos = cabotage ? 1 : 2;
        scrollAndClick(String.format(CARRIES_CABOTAGE, pos));
    }

    public static void saveAndContinue() {
        scrollAndClick(SAVE_AND_CONTINUE);
    }

    public static void saveAndReturn() {
        scrollAndClick(SAVE_AND_RETURN);
    }

}
