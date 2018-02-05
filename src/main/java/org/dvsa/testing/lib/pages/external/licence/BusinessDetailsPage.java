package org.dvsa.testing.lib.pages.external.licence;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.jetbrains.annotations.NotNull;

public class BusinessDetailsPage extends BasePage {
    // Selectors
    private static String LOOKUP = ".lookup";
    private static String COMPANY_LOOKUP_FIELD = LOOKUP + " input[type='text']";
    private static String FIND_COMPANY_BUTTON = LOOKUP + " button[type='submit']";

    private static String COMPANY_NAME_FIELD = "input[name='data[name]']";
    private static String TRADING_NAME_FIELD = "input[name='data[tradingNames][0][name]']";
    private static String NATURE_OF_BUSINESS_FIELD = "input[name='data[natureOfBusiness]']";

    private static String REGISTERED_ADDRESS_TEMPLATE = ".address > div:nth-of-type(%d) input[type='text']";

    private static String BUTTON_WITH_TEXT_TEMPLATE = "//*/button[contains(text(), '%s')]";
    private static String SAVE_AND_CONTINUE_BUTTON = String.format(BUTTON_WITH_TEXT_TEMPLATE, "Save and continue");
    private static String SAVE_AND_RETURN_TO_OVERVIEW = String.format(BUTTON_WITH_TEXT_TEMPLATE, "Save and return to overview");

    // Attributes
    private static String PAGE_TITLE_TEXT = "Business details";
    private static String RESOURCE_PATH = "application/\\d{7}/business-details/";

    // Behaviour
    public static void companyLookup(@NotNull String name) throws UninitialisedDriverException {
        enterField(COMPANY_LOOKUP_FIELD, name);
        click(FIND_COMPANY_BUTTON);
    }

    public static void address(@NotNull AddressLine addressLine, @NotNull String text) throws UninitialisedDriverException {
        enterField(String.format(REGISTERED_ADDRESS_TEMPLATE, addressLine.ordinal()), text);
    }

    public static void saveAndContinueButton() throws UninitialisedDriverException {
        click(SAVE_AND_CONTINUE_BUTTON, SelectorType.XPATH);
    }

    public static void saveAndReturnToOverview() throws UninitialisedDriverException {
        click(SAVE_AND_RETURN_TO_OVERVIEW, SelectorType.XPATH);
    }
}
