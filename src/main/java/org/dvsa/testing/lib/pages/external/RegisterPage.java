package org.dvsa.testing.lib.pages.external;

import activesupport.IllegalBrowserException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.BusinessType;
import org.jetbrains.annotations.NotNull;

public class RegisterPage extends BasePage {

    // Selectors
    private static String USERNAME_FIELD_LOCATOR = nameAttribute("input", "fields[loginId]");
    private static String FIRST_NAME_FIELD = nameAttribute("input", "fields[forename]");
    private static String LAST_NAME_FIELD = nameAttribute("input", "fields[familyName]");
    private static String EMAIL_FIELD = nameAttribute("input", "fields[emailAddress]");
    private static String EMAIL_CONFIRM_FIELD = nameAttribute("input", "fields[emailConfirm]");
    private static String POSSESS_VOL_FIELDSET = "fieldset > fieldset:nth-of-type(1)";
    private static String ORGANISATION_NAME_FIELD = nameAttribute("input", "fields[organisationName]");
    private static String BUSINESS_TYPE_FIELDSET = "fieldset > fieldset:nth-of-type(2) ";
    private static String BUSINESS_TYPE_BUTTON_TEMPLATE = BUSINESS_TYPE_FIELDSET + "> label:nth-of-type(%d) > input";
    private static String WELSH_CORRESPONDENCE_CHECKBOX = "fieldset > div:nth-of-type(8) input[type=\"checkbox\"]";
    private static String TERMS_AND_CONDITIONS_CHECKBOX = "fieldset > div:nth-of-type(9) input[type=\"checkbox\"]";
    private static String CREATE_ACCOUNT_BUTTON = "form > fieldset:nth-of-type(2) button[type=\"submit\"]";

    // Attributes
    private static String PAGE_TITLE_TEXT = "Create an account";
    private static String RESOURCE_PATH = "register/";

    public static String getResourcePath() {
        return RESOURCE_PATH;
    }

    // Behaviour
    public static void username(@NotNull String username) throws IllegalBrowserException {
        enterField(USERNAME_FIELD_LOCATOR, username);
    }

    public static void firstName(@NotNull String firstName) throws IllegalBrowserException {
        enterField(FIRST_NAME_FIELD, firstName);
    }

    public static void lastName(@NotNull String lastName) throws IllegalBrowserException {
        enterField(LAST_NAME_FIELD, lastName);
    }

    public static void email(@NotNull String emailAddress) throws IllegalBrowserException {
        enterField(EMAIL_FIELD, emailAddress);
    }

    public static void emailConfirmation(@NotNull String email) throws IllegalBrowserException {
        enterField(EMAIL_CONFIRM_FIELD, email);
    }

    public static void possessVOL(boolean status) throws IllegalBrowserException {
        int nthTypeSelector = (status) ?  2 : 1;
        String selector = String.format("%s label:nth-of-type(%d) input[type=\"radio\"]", POSSESS_VOL_FIELDSET, nthTypeSelector);

        click(selector);
    }

    public static void organisationName(@NotNull String name) throws IllegalBrowserException {
        enterField(ORGANISATION_NAME_FIELD, name);
    }

    public static void businessType(@NotNull String businessType) throws IllegalBrowserException {
        businessType = businessType.trim().toLowerCase();
        businessType(BusinessType.getEnum(businessType));
    }

    public static void businessType(@NotNull BusinessType businessType) throws IllegalBrowserException {
        click(String.format(BUSINESS_TYPE_BUTTON_TEMPLATE, businessType.ordinal() + 1 ));
    }

    public static void welshCorrespondence(boolean choice) throws IllegalBrowserException {
        if ((choice)) {
            selectWelshCorrespondence();
        } else {
            deselectWelshCorrespondence();
        }
    }

    public static void selectWelshCorrespondence() throws IllegalBrowserException {
        select(WELSH_CORRESPONDENCE_CHECKBOX);
    }

    public static void deselectWelshCorrespondence() throws IllegalBrowserException {
        deselect(WELSH_CORRESPONDENCE_CHECKBOX);
    }

    public static void termsAndCondition(boolean choice) throws IllegalBrowserException {
        if ((choice)) {
            selectTermsAndCondition();
        } else {
            deselectTermsAndCondition();
        }
    }

    public static void selectTermsAndCondition() throws IllegalBrowserException {
        select(TERMS_AND_CONDITIONS_CHECKBOX);
    }

    public static void deselectTermsAndCondition() throws IllegalBrowserException {
        deselect(TERMS_AND_CONDITIONS_CHECKBOX);
    }

    public static void submit() throws IllegalBrowserException {
        click(CREATE_ACCOUNT_BUTTON);
    }

}
