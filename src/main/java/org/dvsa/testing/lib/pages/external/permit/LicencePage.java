package org.dvsa.testing.lib.pages.external.permit;

import activesupport.number.Int;
import activesupport.string.Str;
import org.dvsa.testing.lib.browser.Browser;
import org.dvsa.testing.lib.enums.Duration;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

import java.util.concurrent.TimeUnit;

public class LicencePage extends BasePermitPage {
    private static String CANCEL_BUTTON = "//a[contains(text(), 'Cancel')]";

    private static String TITLE = "h1";
    private static String LICENCE_NTH_LABEL = "label:nth-of-type(%d) ";
    private static String LICENCE_INDEX_TEMPLATE = LICENCE_NTH_LABEL + "input[type=radio]";
    private static String NUM_LICENCE = "label input[type=radio]";
    private static String SELECTED_LICENCE = "label[class*='selected'] input[type=radio]";

    private static String COMMON_LICENCE_PROP = ".form-control--radio";

    private static String LICENCE_REGEX = "(?<=Permit application for licence )\\w{9}";

    final public static String RESOURCE = "(ecmt-add-licence/|ecmt-licence/)";

    public static class AppliedAgainstAllPage {

        final public static String TITLE = "You have already applied against all your licences";

        public static String getTitleOnPage() {
            return getText(LicencePage.TITLE).trim();
        }

    }

    public static class Change {

        private static final String CONFIRM_CHECKBOX = "input#ConfirmChange";
        private static final String SELECTED_CHECKBOX = "label.selected";

        private static final String CONFIRM_LICENCE_CHANGE = "input[type='submit']";

        private static final String CANCEL = "//a[contains(text(), 'Cancel')]";

        public static void confirmChange(boolean confirmation) {
            if (confirmation && !isCheckboxSelected()){
                scrollAndClick(CONFIRM_CHECKBOX);
            } else if (!confirmation && isCheckboxSelected()){
                scrollAndClick(CONFIRM_CHECKBOX);
            }
        }

        private static boolean isCheckboxSelected() {
            untilOnPage();
            return isElementPresent(SELECTED_CHECKBOX);
        }

        public static void confirmLicenceChange() {
            untilElementIsPresent(CONFIRM_LICENCE_CHANGE, Duration.SHORT, TimeUnit.SECONDS);
            scrollAndClick(CONFIRM_LICENCE_CHANGE);
        }

        public static void cancel() {
            untilElementIsPresent(CONFIRM_LICENCE_CHANGE, Duration.SHORT, TimeUnit.SECONDS);
            scrollAndClick(CANCEL, SelectorType.XPATH);
        }

        public static void untilOnPage(){
            untilElementIsPresent("//h1[contains(text(), 'Change Licence')]", SelectorType.XPATH, Duration.MEDIUM, TimeUnit.SECONDS);
        }

    }

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
        Browser.Wait.untilUrlPathIs(RESOURCE, TimeUnit.SECONDS, BasePage.WAIT_TIME_SECONDS);
        untilElementIsPresent(TITLE, SelectorType.CSS, BasePage.WAIT_TIME_SECONDS, TimeUnit.SECONDS);

        return Str.find(LICENCE_REGEX, getText(TITLE));
    }

    public static String getLicenceNumber(int index){
        String selector = String.format(LICENCE_NTH_LABEL, index);

        untilElementIsPresent(selector, SelectorType.CSS, BasePage.WAIT_TIME_SECONDS, TimeUnit.SECONDS);
        return Str.find("\\w{2}\\d{7}", getText(selector));
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

    public static boolean hasMultipleLicences(){
        return isElementPresent(COMMON_LICENCE_PROP);
    }

}
