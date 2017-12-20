package org.dvsa.testing.lib.pages;

import org.jetbrains.annotations.NotNull;

public class LoginPage extends BasePage {

    private static String EMAIL_FIELD_LOCATOR = createNameAttributeSelector("input", "[name=\"username\"]");
    private static String PASSWORD_FIELD_LOCATOR = createNameAttributeSelector("input", "[name=\"password\"]");;
    private static String SUBMIT_BUTTON = createNameAttributeSelector("input", "[name=\"submit\"]");

    public static void email(@NotNull String email) {
        enterField(EMAIL_FIELD_LOCATOR, email);
    }

    public static void password(@NotNull String password) {
        enterField(PASSWORD_FIELD_LOCATOR, password);
    }

    public static void submit(){
        click(SUBMIT_BUTTON);
    }

}