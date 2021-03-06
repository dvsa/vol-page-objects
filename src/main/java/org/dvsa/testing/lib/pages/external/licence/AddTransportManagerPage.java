package org.dvsa.testing.lib.pages.external.licence;

import activesupport.IllegalBrowserException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.jetbrains.annotations.NotNull;

public class AddTransportManagerPage extends BasePage {
    // Selectors
    private static String REGISTERED_USER = "select[name='data[registeredUser]']";

    private static String CONTINUE_BUTTON = "(//*/button[@name='form-actions[continue]'])[2]";
    private static String CANCEL_BUTTON = "//*/button[@name='form-actions[cancel]']";

    /**
     * Selects from a list of already registered users, which includes the operator registered during account creation.
     * @param fullName The full name of a registered user already in the system.
     * @throws IllegalBrowserException
     */
    public static void registeredUser(@NotNull String fullName) throws IllegalBrowserException {
        list(REGISTERED_USER, fullName);
    }

    public static void continueButton() throws IllegalBrowserException {
        click(CONTINUE_BUTTON, SelectorType.XPATH);
    }

    public static void cancelButton() throws IllegalBrowserException {
        click(CANCEL_BUTTON, SelectorType.XPATH);
    }
}
