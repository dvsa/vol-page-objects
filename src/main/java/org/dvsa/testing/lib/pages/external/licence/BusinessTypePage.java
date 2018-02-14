package org.dvsa.testing.lib.pages.external.licence;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.enums.BusinessType;
import org.jetbrains.annotations.NotNull;

public class BusinessTypePage extends LicenceBasePage {
    // Selectors
    private static String BUSINESS_TYPE_TEMPLATE = "label:nth-of-type(%d) input[type='radio']";
    private static String BUTTON_WITH_TEXT_TEMPLATE = "//*/button[contains(text(), '%s')]";

    // Attributes
    private static String PAGE_TITLE_TEXT = "Business type";
    private static String RESOURCE_PATH = "application/\\d{7}/business-type/";

    // Behaviour
    public static void businessType(@NotNull BusinessType businessType) throws UninitialisedDriverException {
        click(String.format(BUSINESS_TYPE_TEMPLATE, businessType.ordinal()));
    }

}
