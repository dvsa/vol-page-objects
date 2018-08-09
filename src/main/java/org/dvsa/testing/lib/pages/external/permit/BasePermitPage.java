package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class BasePermitPage extends BasePage {

    private static String SAVE_AND_CONTINUE ="#submitbutton";
    private static String BACK = "//a[contains(text(), 'Back')]";
    private static String SAVE_AND_RETURN_TO_OVERVIEW = "#save-return-button";

    public static void saveAndContinue(){
        scrollAndClick(SAVE_AND_CONTINUE);
    }

    public static void back(){
        scrollAndClick(BACK, SelectorType.XPATH);
    }

    public static void overview(){
        scrollAndClick(SAVE_AND_RETURN_TO_OVERVIEW);
    }

}
