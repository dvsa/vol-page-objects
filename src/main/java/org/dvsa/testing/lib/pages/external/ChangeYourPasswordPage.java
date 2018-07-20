package org.dvsa.testing.lib.pages.external;

import activesupport.MissingDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.exception.ElementDidNotAppearWithinSpecifiedTimeException;
import org.dvsa.testing.lib.pages.exception.IncorrectPageTitleException;
import org.jetbrains.annotations.NotNull;

public class ChangeYourPasswordPage extends BasePage{

    // Selectors
    private static String CURRENT_PASSWORD_FIELD = nameAttribute("input", "oldPassword");
    private static String NEW_PASSWORD_FIELD = nameAttribute("input", "newPassword");
    private static String CONFIRM_PASSWORD_FIELD = nameAttribute("input", "confirmPassword");
    private static String SUBMIT_BUTTON = nameAttribute("input", "submit");

    // Attributes
    private static String PAGE_TITLE_TEXT = "Change your password";
    private static String RESOURCE_PATH = "auth/expired-password/";

    public static String getResourcePath() {
        return RESOURCE_PATH;
    }

    // Behaviour
    public static void setCurrentPasswordField(@NotNull String currentPasswordField) throws MissingDriverException {
        enterField(CURRENT_PASSWORD_FIELD, currentPasswordField);
    }

    public static void setNewPasswordField(@NotNull String newPasswordField) throws MissingDriverException {
        enterField(NEW_PASSWORD_FIELD, newPasswordField);
    }

    public static void setConfirmPasswordField(@NotNull String confirmPasswordField) throws MissingDriverException {
        enterField(CONFIRM_PASSWORD_FIELD, confirmPasswordField);
    }

    public static void submit() throws MissingDriverException {
        click(SUBMIT_BUTTON);
    }

    public static void untilOnPage() throws MissingDriverException, ElementDidNotAppearWithinSpecifiedTimeException {
        untilElementPresent(SUBMIT_BUTTON);
    }

}
