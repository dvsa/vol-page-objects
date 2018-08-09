package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;

public class BasePermitPage extends BasePage {
    // TODO: Find out selectors for the elements described below
    private static String SAVE_AND_CONTINUE;
    private static String BACK;
    private static String SAVE_AND_RETURN_TO_OVERVIEW;

    public static void saveAndContinue(){
        scrollAndClick(SAVE_AND_CONTINUE);
    }

    public static void back(){
        scrollAndClick(BACK);
    }

    public static void overview(){
        scrollAndClick(SAVE_AND_RETURN_TO_OVERVIEW);
    }

}
