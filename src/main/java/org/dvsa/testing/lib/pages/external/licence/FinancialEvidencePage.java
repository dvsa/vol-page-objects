package org.dvsa.testing.lib.pages.external.licence;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.licence.enums.DeliveryMethod;
import org.jetbrains.annotations.NotNull;

public class FinancialEvidencePage extends BasePage {
    // Selectors
    private static String DELIVERY_METHOD_TEMPLATE = "(//*/input[@type='radio'])[%d]";

    private static String SAVE_AND_CONTINUE_BUTTON = "button[name='form-actions[saveAndContinue]']";
    private static String SAVE_AND_RETURN_TO_OVERVIEW_BUTTON = "button[name='form-actions[saveAndContinue]']";

    // Behaviour
    public static void submitEvidence(@NotNull DeliveryMethod deliveryMethod) throws UninitialisedDriverException {
        click(String.format(DELIVERY_METHOD_TEMPLATE, deliveryMethod.ordinal() + 1), SelectorType.XPATH);
    }

    public static void saveAndContinue() throws UninitialisedDriverException {
        click(SAVE_AND_CONTINUE_BUTTON);
    }

    public static void saveAndReturnToOverview() throws UninitialisedDriverException {
        click(SAVE_AND_RETURN_TO_OVERVIEW_BUTTON);
    }
}
