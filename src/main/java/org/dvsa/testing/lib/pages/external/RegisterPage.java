package org.dvsa.testing.lib.pages.external;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
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
    public static void username(@NotNull String username) throws UninitialisedDriverException {
        enterField(USERNAME_FIELD_LOCATOR, username);
    }

    public static void firstName(@NotNull String firstName) throws UninitialisedDriverException {
        enterField(FIRST_NAME_FIELD, firstName);
    }

    public static void lastName(@NotNull String lastName) throws UninitialisedDriverException {
        enterField(LAST_NAME_FIELD, lastName);
    }

    public static void email(@NotNull String emailAddress) throws UninitialisedDriverException {
        enterField(EMAIL_FIELD, emailAddress);
    }

    public static void emailConfirmation(@NotNull String email) throws UninitialisedDriverException {
        enterField(EMAIL_CONFIRM_FIELD, email);
    }

    public static void possessVOL(boolean status) throws UninitialisedDriverException {
        int nthTypeSelector = (status) ?  2 : 1;
        String selector = String.format("%s label:nth-of-type(%d) input[type=\"radio\"]", POSSESS_VOL_FIELDSET, nthTypeSelector);

        click(selector);
    }

    public static void organisationName(@NotNull String name) throws UninitialisedDriverException {
        enterField(ORGANISATION_NAME_FIELD, name);
    }

    public static void businessType(@NotNull String businessType) throws UninitialisedDriverException {
        businessType = businessType.trim().toLowerCase();
        businessType(BusinessType.getEnum(businessType));
    }

    public static void businessType(@NotNull BusinessType businessType) throws UninitialisedDriverException {
        int position;

        switch(businessType){
            case LIMITED_COMPANY:
                position = 1;
                break;
            case SOLE_TRADER:
                position = 2;
                break;
            case PARTNERSHIP:
                position = 3;
                break;
            case LIMITED_LIABILITY_COMPANY:
                position = 4;
                break;
            case OTHER:
                position = 5;
                break;
            default:
                throw new IllegalArgumentException(String.format("%s is not a supported business type", businessType));
        }

        click(String.format(BUSINESS_TYPE_BUTTON_TEMPLATE, position));
    }

    public static void welshCorrespondence(boolean choice) throws UninitialisedDriverException {
        if ((choice)) {
            selectWelshCorrespondence();
        } else {
            deselectWelshCorrespondence();
        }
    }

    public static void selectWelshCorrespondence() throws UninitialisedDriverException {
        select(WELSH_CORRESPONDENCE_CHECKBOX);
    }

    public static void deselectWelshCorrespondence() throws UninitialisedDriverException {
        deselect(WELSH_CORRESPONDENCE_CHECKBOX);
    }

    public static void termsAndCondition(boolean choice) throws UninitialisedDriverException {
        if ((choice)) {
            selectTermsAndCondition();
        } else {
            deselectTermsAndCondition();
        }
    }

    public static void selectTermsAndCondition() throws UninitialisedDriverException {
        select(TERMS_AND_CONDITIONS_CHECKBOX);
    }

    public static void deselectTermsAndCondition() throws UninitialisedDriverException {
        deselect(TERMS_AND_CONDITIONS_CHECKBOX);
    }

    public static void submit() throws UninitialisedDriverException {
        click(CREATE_ACCOUNT_BUTTON);
    }

}
