package org.dvsa.testing.lib.pages.external.licence;

import activesupport.MissingDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.exception.ElementDidNotAppearWithinSpecifiedTimeException;
import org.dvsa.testing.lib.pages.external.licence.enums.Calendar;
import org.dvsa.testing.lib.pages.external.licence.enums.TypeOfManager;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.util.List;

public class TransportManagerDetailsPage extends BasePage {
    // Selectors
    private static String DATE_OF_BIRTH_TEMPLATE = "fieldset.date > div:nth-of-type(%d) input";
    private static String EMAIL_ADDRESS = "input[name='details[emailAddress]']";
    private static String PLACE_OF_BIRTH = "input[name='details[birthPlace]']";

    private static String HOME_ADDRESS = "fieldset[data-group='homeAddress[searchPostcode]']";
    private static String HOME_ADDRESS_SEARCH = HOME_ADDRESS + " input[type='text']";
    private static String FIND_HOME_ADDRESS_BUTTON = HOME_ADDRESS + " button[type='submit']";
    private static String FOUND_HOME_ADDRESSES_LIST = HOME_ADDRESS + " select";

    private static String WORK_ADDRESS = "fieldset[data-group='workAddress']";
    private static String WORK_ADDRESS_SEARCH = WORK_ADDRESS + " input[type='text']";
    private static String FIND_WORK_ADDRESS_BUTTON = WORK_ADDRESS + " button[type='submit']";
    private static String FOUND_WORK_ADDRESSES_LIST = WORK_ADDRESS + " select";

    private static String RESPONSIBILITIES_TEMPLATE = "fieldset[data-group='responsibilities'] fieldset:nth-of-type(%d)";
    private static String TYPE_OF_MANAGER = String.format(RESPONSIBILITIES_TEMPLATE + " > label:nth-of-type(%d)", 1);
    private static String OWNER_OR_DIRECTOR = String.format(RESPONSIBILITIES_TEMPLATE + " > label:nth-of-type(%d)", 2);
    private static String TRANSPORT_MANAGER_TIME_ALLOCATION = String.format(RESPONSIBILITIES_TEMPLATE, 3) + " > fieldset > div:nth-of-type(%d)";

    private static String REASON = "textarea[name='responsibilities[additionalInformation]']";

    private static String SAVE_AND_CONTINUE = "button[name='form-actions[submit]']";
    private static String SAVE_AND_RETURN_TO_TRANSPORT_MANAGERS = "button[name='form-actions[save]']";

    // Behaviour
    public static void dateOfBirth(@NotNull Calendar field, @NotNull String date) throws MissingDriverException {
        enterField(String.format(DATE_OF_BIRTH_TEMPLATE, field.ordinal() + 1), date);
    }

    public static void emailAddress(@NotNull String emailAddress) throws MissingDriverException {
        enterField(EMAIL_ADDRESS, emailAddress);
    }

    public static void placeOfBirth(@NotNull String place) throws MissingDriverException {
        enterField(PLACE_OF_BIRTH, place);
    }

    public static List<String> homeAddressSearch(@NotNull String postCode) throws MissingDriverException, ElementDidNotAppearWithinSpecifiedTimeException {
        enterField(HOME_ADDRESS_SEARCH, postCode);
        TransportManagerDetailsPage.findHomeAddressButton();
        untilElementPresentWithin(FOUND_HOME_ADDRESSES_LIST, 5000);
        return getListValues(FOUND_HOME_ADDRESSES_LIST);
    }

    private static void findHomeAddressButton() throws MissingDriverException {
        click(FIND_HOME_ADDRESS_BUTTON);
    }

    public static void homeAddress(@NotNull String optionValue) throws MissingDriverException {
        list(FOUND_HOME_ADDRESSES_LIST, optionValue);
    }

    public static List<String> workAddressSearch(@NotNull String postCode) throws MissingDriverException, ElementDidNotAppearWithinSpecifiedTimeException {
        enterField(WORK_ADDRESS_SEARCH, postCode);
        TransportManagerDetailsPage.findWorkAddressButton();
        untilElementPresentWithin(FOUND_WORK_ADDRESSES_LIST, 5000);
        return getListValues(FOUND_WORK_ADDRESSES_LIST);
    }

    private static void findWorkAddressButton() throws MissingDriverException {
        click(FIND_WORK_ADDRESS_BUTTON);
    }

    public static void workAddress(@NotNull String optionValue) throws MissingDriverException {
        list(FOUND_WORK_ADDRESSES_LIST, optionValue);
    }

    public static void typeOfManager(@NotNull TypeOfManager typeOfManager) throws MissingDriverException {
        click(String.format(TYPE_OF_MANAGER, typeOfManager.ordinal() + 1));
    }

    public static void isOwnerOrDirector(boolean isOwnerOrDirector) throws MissingDriverException {
        int elementPosition = isOwnerOrDirector ? 1 : 2;
        click(String.format(OWNER_OR_DIRECTOR, elementPosition));
    }

    public static void transportManagerTimeAllocation(@NotNull DayOfWeek dayOfWeek, float hours) throws MissingDriverException {
        enterField(String.format(TRANSPORT_MANAGER_TIME_ALLOCATION, dayOfWeek.getValue()), String.valueOf(hours));
    }

    public static void reason(@NotNull String reason) throws MissingDriverException {
        enterField(REASON, reason);
    }

    public static void saveAndContinue() throws MissingDriverException {
        click(SAVE_AND_CONTINUE);
    }

    public static void saveAndReturnToTransportManagers() throws MissingDriverException {
        click(SAVE_AND_RETURN_TO_TRANSPORT_MANAGERS);
    }
}
