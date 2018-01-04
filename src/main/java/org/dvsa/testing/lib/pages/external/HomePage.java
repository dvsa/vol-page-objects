package org.dvsa.testing.lib.pages.external;

import org.dvsa.testing.lib.pages.BasePage;

public class HomePage extends BasePage {
    // Selectors
    private static String APPLY_FOR_LICENCE_BUTTON = "div > a[class=\"action--primary large\"]";

    // Attributes
    private static String PAGE_TITLE_TEXT = "Home";


    public static void applyForLicenceButton(){
        click(APPLY_FOR_LICENCE_BUTTON);
    }

    public static boolean isCurrentPage(){
        return isCurrentPage(PAGE_TITLE, PAGE_TITLE_TEXT);
    }
}
