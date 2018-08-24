package org.dvsa.testing.lib.pages.external.permit;

import activesupport.number.Int;
import activesupport.string.Str;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class LicencePage extends BasePermitPage {
    // TODO: Assign selectors when their values are defined
    private static String CANCEL_BUTTON = "//input[@name='Fields[Cancel]']";

    private static String TITLE = "h1";
    private static String LICENCE_NTH_LABEL = "label:nth-of-type(%d) ";
    private static String LICENCE_INDEX_TEMPLATE = LICENCE_NTH_LABEL + "input[type=radio]";
    private static String NUM_LICENCE = "label input[type=radio]";
    private static String SELECTED_LICENCE = "label[class*='selected'] input[type=radio]";

    private static String LICENCE_REGEX = "(?<=Permit application for licence )\\w{9}";

    public static String randomLicnece(){
        int index = Int.random(1, numOfLicences());
        licence(index);
        return getLicenceNumber(index);
    }

    public static boolean hasSelectedLicence(){
        return isElementPresent(SELECTED_LICENCE);
    }

    public static boolean hasNoSelectedLicence(){
        return !hasSelectedLicence();
    }

    public static String getLicenceNumber(){
        return Str.find(LICENCE_REGEX, getText(TITLE));
    }

    public static String getLicenceNumber(int index){
        return Str.find("\\w{2}\\d{7}", getText(String.format(LICENCE_NTH_LABEL, index)));
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

    public static boolean hasErrorMessagePresent(){
        return isElementPresent(".error__text");
    }

}
