package org.dvsa.testing.lib.pages.external;

import activesupport.system.out.Output;
import org.dvsa.testing.lib.pages.enums.BusinessType;
import org.jetbrains.annotations.NotNull;
import org.dvsa.testing.lib.pages.BasePage;

public class RegisterPage extends BasePage {
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

    public static void username(@NotNull String username){
        enterField(USERNAME_FIELD_LOCATOR, username);
    }

    public static void firstName(@NotNull String firstName){
        enterField(FIRST_NAME_FIELD, firstName);
    }

    public static void lastName(@NotNull String lastName){
        enterField(LAST_NAME_FIELD, lastName);
    }

    public static void email(@NotNull String emailAddress){
        enterField(EMAIL_FIELD, emailAddress);
    }

    public static void emailConfirmation(@NotNull String email){
        enterField(EMAIL_CONFIRM_FIELD, email);
    }

    public static void possessVOL(boolean status){
        int nthTypeSelector = (status) ?  2 : 1;
        String selector = String.format("%s label:nth-of-type(%d) input[type=\"radio\"]", POSSESS_VOL_FIELDSET, nthTypeSelector);

        click(selector);
    }

    public static void organisationName(@NotNull String name){
        enterField(ORGANISATION_NAME_FIELD, name);
    }

    public static void businessType(@NotNull String businessType){
        businessType(enumBusinessType(businessType));
    }

    private static BusinessType enumBusinessType(@NotNull String businessType){
        businessType = businessType.trim().toLowerCase();
        BusinessType enumBusinessType;

        switch(businessType){
            case "limited company":
                enumBusinessType = BusinessType.LIMITED_COMPANY;
                break;
            case "sole trader":
                enumBusinessType = BusinessType.SOLE_TRADER;
                break;
            case "partnership":
                enumBusinessType = BusinessType.PARTNERSHIP;
                break;
            case "limited liability company":
                enumBusinessType = BusinessType.LIMITED_LIABILITY_COMPANY;
                break;
            case "other":
                enumBusinessType = BusinessType.OTHER;
                break;
            default:
                throw new IllegalArgumentException(Output.printColoredLog("[ERROR] Unable to convert " + businessType + " into a BusinessType enum"));
        }

        return enumBusinessType;
    }

    public static void businessType(@NotNull BusinessType businessType){
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

    public static void welshCorrespondence(boolean choice){
        if ((choice)) {
            selectWelshCorrespondence();
        } else {
            deselectWelshCorrespondence();
        }
    }

    public static void selectWelshCorrespondence(){
        select(WELSH_CORRESPONDENCE_CHECKBOX);
    }

    public static void deselectWelshCorrespondence(){
        deselect(WELSH_CORRESPONDENCE_CHECKBOX);
    }

    public static void termsAndCondition(boolean choice){
        if ((choice)) {
            selectTermsAndCondition();
        } else {
            deselectTermsAndCondition();
        }
    }

    public static void selectTermsAndCondition(){
        select(TERMS_AND_CONDITIONS_CHECKBOX);
    }

    public static void deselectTermsAndCondition(){
        deselect(TERMS_AND_CONDITIONS_CHECKBOX);
    }

    public static void submit(){
        click(CREATE_ACCOUNT_BUTTON);
    }
}
