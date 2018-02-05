package org.dvsa.testing.lib.pages.external.licence;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.jetbrains.annotations.NotNull;

public class ApplyForNewLicencePage extends BasePage {
    // Selectors
    private static String OVERVIEW_LIST_ITEM_TEMPLATE = ".overview__list li:nth-of-type(%d)";


    // Attribute
    private static String PAGE_TITLE_TEXT = "Apply for a new licence";
    private static String RESOURCE_PATH = "application/\\d{7}/";

    // Behaviour

    public static void section(@NotNull ApplicationSection applicationSection) throws UninitialisedDriverException {
        click(String.format(OVERVIEW_LIST_ITEM_TEMPLATE, applicationSection.ordinal() + 1));
    }
}
