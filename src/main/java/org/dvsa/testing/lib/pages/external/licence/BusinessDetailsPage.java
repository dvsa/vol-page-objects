package org.dvsa.testing.lib.pages.external.licence;

import activesupport.IllegalBrowserException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.jetbrains.annotations.NotNull;

public class BusinessDetailsPage extends BasePage {
    // Selectors
    private static String LOOKUP = ".lookup";
    private static String COMPANY_LOOKUP_FIELD = LOOKUP + " input[type='text']";
    private static String FIND_COMPANY_BUTTON = LOOKUP + " button[type='submit']";

    private static String COMPANY_NAME_FIELD = "input[name='data[name]']";
    private static String TRADING_NAME_FIELD_TEMPLATE = "input[name='data[tradingNames][%d][name]']";
    private static String ADD_ANOTHER_TRADING_NAME = "//*/a[contains(text(), 'Add another trading name')]";
    private static String NATURE_OF_BUSINESS_FIELD = "input[name='data[natureOfBusiness]']";

    private static String REGISTERED_ADDRESS_TEMPLATE = ".address > div:nth-of-type(%d) input[type='text']";
    private static String ADD_SUBSIDIARY = "//*/button[contains(text(), 'Add subsidiary')]";

    private static String BUTTON_WITH_TEXT_TEMPLATE = "//*/button[contains(text(), '%s')]";
    private static String SAVE_AND_CONTINUE_BUTTON = String.format(BUTTON_WITH_TEXT_TEMPLATE, "Save and continue");
    private static String SAVE_AND_RETURN_TO_OVERVIEW = String.format(BUTTON_WITH_TEXT_TEMPLATE, "Save and return to overview");

    // Attributes
    private static String PAGE_TITLE_TEXT = "Business details";
    private static String RESOURCE_PATH = "application/\\d{7}/business-details/";

    // Behaviour
    public static void companyLookup(@NotNull String name) throws IllegalBrowserException {
        enterField(COMPANY_LOOKUP_FIELD, name);
        click(FIND_COMPANY_BUTTON);
    }

    public static void tradingName(@NotNull String... names) throws IllegalBrowserException {
        for (int pos = 0; pos <= names.length; pos++) {
            enterField(String.format(TRADING_NAME_FIELD_TEMPLATE, pos), names[pos]);

            // Clicks add another trading if there are more names leftover that were given
            if (pos + 1 < names.length) {
                click(ADD_ANOTHER_TRADING_NAME, SelectorType.XPATH);
            }
        }
    }

    public static void natureOfBusiness(@NotNull String natureOfBusiness) throws IllegalBrowserException {
        enterField(NATURE_OF_BUSINESS_FIELD, natureOfBusiness);
    }

    public static void address(@NotNull AddressLine addressLine, @NotNull String text) throws IllegalBrowserException {
        enterField(String.format(REGISTERED_ADDRESS_TEMPLATE, addressLine.ordinal()), text);
    }

    public static void addSubsidiary() throws IllegalBrowserException {
        click(ADD_SUBSIDIARY, SelectorType.XPATH);
    }

    public static void saveAndContinueButton() throws IllegalBrowserException {
        click(SAVE_AND_CONTINUE_BUTTON, SelectorType.XPATH);
    }

    public static void saveAndReturnToOverview() throws IllegalBrowserException {
        click(SAVE_AND_RETURN_TO_OVERVIEW, SelectorType.XPATH);
    }
}
