package org.dvsa.testing.lib.pages.external.licence;

import activesupport.IllegalBrowserException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class DirectorsPage extends BasePage {
    // Selectors
    private static String ADD_DIRECTOR_BUTTON = "button[name='table[action]']";

    private static String BUTTON_WITH_TEXT_TEMPLATE = "//*/button[contains(text(), '%s')]";
    private static String SAVE_AND_CONTINUE_BUTTON = String.format(BUTTON_WITH_TEXT_TEMPLATE, "Save and continue");
    private static String SAVE_AND_RETURN_TO_OVERVIEW = String.format(BUTTON_WITH_TEXT_TEMPLATE, "Save and return to overview");
    // Attributes
    private static String PAGE_TITLE_TEXT = "Directors";
    private static String RESOURCE_PATH = "application/\\d{7}/people/";

    // Behaviour
    public static void addDirectorButton() throws IllegalBrowserException {
        click(ADD_DIRECTOR_BUTTON);
    }

    public static void saveAndContinueButton() throws IllegalBrowserException {
        click(SAVE_AND_CONTINUE_BUTTON, SelectorType.XPATH);
    }

    public static void saveAndReturnToOverview() throws IllegalBrowserException {
        click(SAVE_AND_RETURN_TO_OVERVIEW, SelectorType.XPATH);
    }
}
