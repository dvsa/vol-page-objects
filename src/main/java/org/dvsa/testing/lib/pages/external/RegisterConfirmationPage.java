package org.dvsa.testing.lib.pages.external;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

import java.util.regex.Pattern;

public class RegisterConfirmationPage extends BasePage {
    private static String CONFIRM_EMAIL_TEMPPASS_SENT_MESSAGE = ".guidance p";
    private static String EMAIL_NOT_RECIVEVED_MESSAGE = ".article p:nth-of-type(2)";
    private static String CHECK_EMAIL = "We have sent an email to .+\\@.+\\..+ containing a temporary password. Once you’ve signed in using the temporary password you will need to create a new password";
    private static String SIGNING_IN_PROBLEMS = "If our email does not appear in your inbox within 5 minutes, check your Spam or Junk folder or contact your IT department for help. If our email is in your Spam or Junk folder, please change your email settings so that further emails from us are directed to your inbox. If you have issues signing in or didn’t receive our email you will need to contact notifications@vehicle-operator-licensing.service.gov.uk.";

    public static String getConfirmEmailTemppassSentMessageText() throws UninitialisedDriverException {
        return getText(CONFIRM_EMAIL_TEMPPASS_SENT_MESSAGE, SelectorType.CSS);
    }

    public static String getEmailNotRecivevedMessage() throws UninitialisedDriverException {
        return getText(EMAIL_NOT_RECIVEVED_MESSAGE, SelectorType.CSS);
    }

    //Attributes
    public static Pattern checkEmail() {
        return
                Pattern.compile(CHECK_EMAIL);
    }

    public static String signingInProblems() {
        return SIGNING_IN_PROBLEMS;
    }

}
