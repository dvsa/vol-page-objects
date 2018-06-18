package org.dvsa.testing.lib.pages;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.jetbrains.annotations.NotNull;

public class LoginPage extends BasePage {

    // Selectors
    private static String EMAIL_FIELD_LOCATOR = nameAttribute("input", "username");
    private static String PASSWORD_FIELD_LOCATOR = nameAttribute("input", "password");
    private static String SUBMIT_BUTTON = nameAttribute("input", "submit") + "[value=\"Sign in\"]";

    // Attributes
    private static String RESOURCE_PATH = "auth/login/";

    public static String getResourcePath() {
        return RESOURCE_PATH;
    }

    // Behaviour
    public static void email(@NotNull String email) throws UninitialisedDriverException {
        enterField(EMAIL_FIELD_LOCATOR, email);
    }

    public static void password(@NotNull String password) throws UninitialisedDriverException {
        enterField(PASSWORD_FIELD_LOCATOR, password);
    }

    public static void submit() throws UninitialisedDriverException {
        click(SUBMIT_BUTTON);
    }

    public static void signIn(String emailAddress, String password) {
        LoginPage.email(emailAddress);
        LoginPage.password(password);
        LoginPage.submit();
    }

}