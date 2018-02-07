package org.dvsa.testing.lib.pages.external.licence;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.licence.enums.DeliveryMethod;
import org.jetbrains.annotations.NotNull;

public class FinancialEvidencePage extends BasePage {
    // Selectors
    private static String DELIVERY_METHOD_TEMPLATE = "(//*/input[@type='radio'])[%d]";

    // Behaviour
    public static void submitEvidence(@NotNull DeliveryMethod deliveryMethod) throws UninitialisedDriverException {
        click(String.format(DELIVERY_METHOD_TEMPLATE, deliveryMethod.ordinal() + 1), SelectorType.XPATH);
    }
}
