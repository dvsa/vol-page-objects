package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

import java.util.concurrent.TimeUnit;

public class BasePermitPage extends BasePage {

    private static String SAVE_AND_CONTINUE ="#submitbutton";
    private static String BACK = "//a[contains(text(), 'Back')]";
    private static String SAVE_AND_RETURN_TO_OVERVIEW = "#save-return-button";

    public static void saveAndContinue(){
        untilElementIsPresent(SAVE_AND_CONTINUE, SelectorType.CSS, BasePermitPage.WAIT_TIME_SECONDS, TimeUnit.SECONDS);
        scrollAndClick(SAVE_AND_CONTINUE);
    }

    public static void back(){
        untilElementIsPresent(BACK, SelectorType.XPATH, BasePermitPage.WAIT_TIME_SECONDS, TimeUnit.SECONDS);
        scrollAndClick(BACK, SelectorType.XPATH);
    }

    public static void overview(){
        untilElementIsPresent(SAVE_AND_RETURN_TO_OVERVIEW, SelectorType.CSS, BasePermitPage.WAIT_TIME_SECONDS, TimeUnit.SECONDS);
        scrollAndClick(SAVE_AND_RETURN_TO_OVERVIEW);
    }

}
