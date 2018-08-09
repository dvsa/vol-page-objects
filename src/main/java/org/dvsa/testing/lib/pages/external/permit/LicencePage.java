package org.dvsa.testing.lib.pages.external.permit;

import activesupport.string.Str;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class LicencePage extends BasePermitPage {
    // TODO: Assign selectors when their values are defined
    private static String CANCEL_BUTTON = "//input[@name='Fields[Cancel]']";

    private static String TITLE = "h1";
    private static String LICENCE_INDEX_TEMPLATE = "label:nth-of-type(%d) input[type=radio]";
    private static String NUM_LICENCE = "label input[type=radio]";
    private static String SELECTED_LICENCE = "label[class*='selected'] input[type=radio]";

    private static String LICENCE_REGEX = "(?<=Permit application for licence )\\w{9}";

    public static boolean hasSelectedLicence(){
        return isElementPresent(SELECTED_LICENCE);
    }

    public static boolean hasNoSelectedLicence(){
        return !hasSelectedLicence();
    }

    public static String getLicenceNumber(){
        return Str.find(LICENCE_REGEX, getText(TITLE));
    }

    public static int numOfLicences(){
        return size(NUM_LICENCE);
    }

    public static void licence(int index){
        scrollAndClick(String.format(LICENCE_INDEX_TEMPLATE, index));
    }

    public static void cancel(){
        scrollAndClick(CANCEL_BUTTON, SelectorType.XPATH);
    }

}