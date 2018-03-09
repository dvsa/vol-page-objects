package org.dvsa.testing.lib.pages.external;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class RegisterConfirmationPage extends BasePage {
    private static String CONFIRM_EMAIL_TEMPPASS_SENT_MESSAGE = ".guidance p";
    private static String EMAIL_NOT_RECIVEVED_MESSAGE = ".article p:nth-of-type(2)";


    public static String getConfirmEmailTemppassSentMessageText() throws UninitialisedDriverException {
        return getText(CONFIRM_EMAIL_TEMPPASS_SENT_MESSAGE, SelectorType.CSS);
    }

    public static String getEmailNotRecivevedMessage() throws UninitialisedDriverException {
        return getText(EMAIL_NOT_RECIVEVED_MESSAGE, SelectorType.CSS);
    }
}
