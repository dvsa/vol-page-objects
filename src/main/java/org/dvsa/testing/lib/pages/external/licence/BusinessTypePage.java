package org.dvsa.testing.lib.pages.external.licence;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.BusinessType;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.jetbrains.annotations.NotNull;

public class BusinessTypePage extends BasePage {
    // Selectors
    private static String BUSINESS_TYPE_TEMPLATE = "label:nth-of-type(%d) input[type='radio']";
    private static String BUTTON_WITH_TEXT_TEMPLATE = "//*/button[contains(text(), '%s')]";
    private static String SAVE_AND_CONTINUE_BUTTON = String.format(BUTTON_WITH_TEXT_TEMPLATE, "Save and continue");
    private static String SAVE_AND_RETURN_TO_OVERVIEW = String.format(BUTTON_WITH_TEXT_TEMPLATE, "Save and return to overview");


    // Attributes
    private static String PAGE_TITLE_TEXT = "Business type";
    private static String RESOURCE_PATH = "application/\\d{7}/business-type/";

    // Behaviour
    public static void businessType(@NotNull BusinessType businessType) throws UninitialisedDriverException {
        click(String.format(BUSINESS_TYPE_TEMPLATE, businessType.ordinal()));
    }

    public static void saveAndContinueButton() throws UninitialisedDriverException {
        click(SAVE_AND_CONTINUE_BUTTON, SelectorType.XPATH);
    }

    public static void saveAndReturnToOverview() throws UninitialisedDriverException {
        click(SAVE_AND_RETURN_TO_OVERVIEW, SelectorType.XPATH);
    }
}
