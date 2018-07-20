package org.dvsa.testing.lib.pages.external.licence;

import activesupport.MissingDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.licence.enums.Phone;
import org.jetbrains.annotations.NotNull;

public class AddressesPage extends BasePage {
    // Selectors
    private static String ATTENTION_OF_FIELD = "input[name='correspondence[fao]']";
    private static String CORRESPONDENCE_POST_CODE_SEARCH = "input[name='correspondence_address[searchPostcode][postcode]']";
    private static String FIND_BUTTON_TEMPLATE = "(//*/button[contains(text(), 'Find address')])[%d]";
    private static String CORRESPONDENCE_FIND_ADDRESS_BUTTON = String.format(FIND_BUTTON_TEMPLATE, 1);

    private static String PRIMARY_CONTACT_NUMBER_FIELD = "input[name='contact[phone_primary]']";
    private static String SECONDARY_CONTACT_NUMBER_FIELD = "input[name='contact[phone_secondary]']";
    private static String EMAIL_ADDRESS_FIELD = "input[name='contact[email]']";

    private static String ESTABLISHMENT_POST_CODE_SEARCH = "input[name='establishment_address[searchPostcode][postcode]']";
    private static String ESTABLISHMENT_FIND_ADDRESS_BUTTON = String.format(FIND_BUTTON_TEMPLATE, 2);

    private static String BUTTON_WITH_TEXT_TEMPLATE = "//*/button[contains(text(), '%s')]";
    private static String SAVE_AND_CONTINUE_BUTTON = String.format(BUTTON_WITH_TEXT_TEMPLATE, "Save and continue");
    private static String SAVE_AND_RETURN_TO_OVERVIEW = String.format(BUTTON_WITH_TEXT_TEMPLATE, "Save and return to overview");
    // Attributes
    private static String PAGE_TITLE_TEXT = "Addresses";
    private static String RESOURCE_PATH = "application/\\d{7}/addresses/";
    // Behaviour
    public static void attentionOf(@NotNull String name) throws MissingDriverException {
        enterField(ATTENTION_OF_FIELD, name);
    }

    public static void findAddressForCorrespondence(@NotNull String postCode) throws MissingDriverException {
        enterField(CORRESPONDENCE_POST_CODE_SEARCH, postCode);
        click(CORRESPONDENCE_FIND_ADDRESS_BUTTON, SelectorType.XPATH);
    }

    public static void contactNumber(@NotNull String number, @NotNull Phone phone) throws MissingDriverException {
        String selector = phone.equals(Phone.PRIMARY) ? PRIMARY_CONTACT_NUMBER_FIELD : SECONDARY_CONTACT_NUMBER_FIELD;
        enterField(selector, number);
    }

    public static void contactNumber(@NotNull String primaryNumber) throws MissingDriverException {
        contactNumber(primaryNumber, Phone.PRIMARY);
    }

    public static void contactNumber(@NotNull String primaryNumber, @NotNull String secondaryNumber) throws MissingDriverException {
        contactNumber(primaryNumber, Phone.PRIMARY);
        contactNumber(secondaryNumber, Phone.SECONDARY);
    }

    public static void emailAddress(@NotNull String emailAddress) throws MissingDriverException {
        enterField(EMAIL_ADDRESS_FIELD, emailAddress);
    }

    public static void findAddressForEstablishment(@NotNull String postCode) throws MissingDriverException {
        enterField(ESTABLISHMENT_POST_CODE_SEARCH, postCode);
        click(ESTABLISHMENT_FIND_ADDRESS_BUTTON, SelectorType.XPATH);
    }

    public static void saveAndContinueButton() throws MissingDriverException {
        click(SAVE_AND_CONTINUE_BUTTON, SelectorType.XPATH);
    }

    public static void saveAndReturnToOverview() throws MissingDriverException {
        click(SAVE_AND_RETURN_TO_OVERVIEW, SelectorType.XPATH);
    }

}
