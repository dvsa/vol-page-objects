package org.dvsa.testing.lib.pages.external;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
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
    public static void setCurrentPasswordField(@NotNull String currentPasswordField) throws UninitialisedDriverException {
        enterField(CURRENT_PASSWORD_FIELD, currentPasswordField);
    }

    public static void setNewPasswordField(@NotNull String newPasswordField) throws UninitialisedDriverException {
        enterField(NEW_PASSWORD_FIELD, newPasswordField);
    }

    public static void setConfirmPasswordField(@NotNull String confirmPasswordField) throws UninitialisedDriverException {
        enterField(CONFIRM_PASSWORD_FIELD, confirmPasswordField);
    }

    public static void submit() throws UninitialisedDriverException {
        click(SUBMIT_BUTTON);
    }

    public static void untilExpectedPageTitle() throws IncorrectPageTitleException, UninitialisedDriverException {
        untilExpectedPageTitle(PAGE_TITLE_TEXT);
    }

    public static void untilExpectedPageTitle(long horizonMilliseconds) throws IncorrectPageTitleException, UninitialisedDriverException {
        untilExpectedPageTitle(PAGE_TITLE_TEXT, horizonMilliseconds);
    }

    public static boolean isExpectedPageTitle() throws UninitialisedDriverException {
        return BasePage.isExpectedPageTitle(PAGE_TITLE_TEXT);
    }

    public static boolean isExpectedPageTitle(long horizonMilliseconds) throws UninitialisedDriverException {
        return BasePage.isExpectedPageTitle(PAGE_TITLE_TEXT, horizonMilliseconds);
    }

    public static boolean isNotExpectedPageTile() throws UninitialisedDriverException {
        return BasePage.isNotExpectedPageTitle(PAGE_TITLE_TEXT);
    }

    public static boolean isNotExpectedPageTile(long horizonMilliseconds) throws UninitialisedDriverException {
        return BasePage.isNotExpectedPageTitle(PAGE_TITLE_TEXT, horizonMilliseconds);
    }

}
