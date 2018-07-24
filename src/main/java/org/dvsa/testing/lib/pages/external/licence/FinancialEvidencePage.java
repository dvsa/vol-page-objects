package org.dvsa.testing.lib.pages.external.licence;

import activesupport.IllegalBrowserException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.licence.enums.DeliveryMethod;
import org.jetbrains.annotations.NotNull;

public class FinancialEvidencePage extends BasePage {
    // Selectors
    private static String DELIVERY_METHOD_TEMPLATE = "(//*/input[@type='radio'])[%d]";

    private static String SAVE_AND_CONTINUE_BUTTON = "//*/button[contains(text(), 'Save and continue')]";
    private static String SAVE_AND_RETURN_TO_OVERVIEW_BUTTON = "//*/button[contains(text(), 'Save and return to overview')]";

    // Behaviour
    public static void submitEvidence(@NotNull DeliveryMethod deliveryMethod) throws IllegalBrowserException {
        click(String.format(DELIVERY_METHOD_TEMPLATE, deliveryMethod.ordinal() + 1), SelectorType.XPATH);
    }

    public static void saveAndContinue() throws IllegalBrowserException {
        click(SAVE_AND_CONTINUE_BUTTON, SelectorType.XPATH);
    }

    public static void saveAndReturnToOverview() throws IllegalBrowserException {
        click(SAVE_AND_RETURN_TO_OVERVIEW_BUTTON, SelectorType.XPATH);
    }
}
