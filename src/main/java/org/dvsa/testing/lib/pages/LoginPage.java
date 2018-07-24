package org.dvsa.testing.lib.pages;

import activesupport.IllegalBrowserException;
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
    public static void email(@NotNull String email) throws IllegalBrowserException {
        enterField(EMAIL_FIELD_LOCATOR, email);
    }

    public static void password(@NotNull String password) throws IllegalBrowserException {
        enterField(PASSWORD_FIELD_LOCATOR, password);
    }

    public static void submit() throws IllegalBrowserException {
        click(SUBMIT_BUTTON);
    }

    public static void untilNotOnPage() throws IllegalBrowserException {
        int seconds = 30;
        untilNotOnPage(seconds);
    }

    public static void untilNotOnPage(int seconds) throws IllegalBrowserException {
        untilNotInDOM(SUBMIT_BUTTON, seconds);
    }

}