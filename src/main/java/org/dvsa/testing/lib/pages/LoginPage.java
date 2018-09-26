package org.dvsa.testing.lib.pages;

import org.jetbrains.annotations.NotNull;

public class LoginPage extends BasePage {

    // Selectors
    private static String USERNAME_FIELD_LOCATOR = "input[name='username']";
    private static String PASSWORD_FIELD_LOCATOR = "input[name='password']";
    private static String SUBMIT_BUTTON = "input[name='submit']";

    // Attributes
    private static String RESOURCE_PATH = "auth/login/";

    public static String getResourcePath() {
        return RESOURCE_PATH;
    }

    // Behaviour
    public static void username(@NotNull String username) {
        scrollAndEnterField(USERNAME_FIELD_LOCATOR, username);
    }

    public static void password(@NotNull String password) {
        scrollAndEnterField(PASSWORD_FIELD_LOCATOR, password);
    }

    public static void submit() {
        scrollAndClick(SUBMIT_BUTTON);
    }

    public static void signIn(String username, String password) {
        LoginPage.username(username);
        LoginPage.password(password);
        LoginPage.submit();
    }

}