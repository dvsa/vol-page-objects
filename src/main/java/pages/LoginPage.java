package pages;

import org.jetbrains.annotations.NotNull;

public class LoginPage extends BasePage {

    private static String EMAIL_FIELD_LOCATOR = createNameAttributeSelector("input", "[name=\"username\"]");
    private static String PASSWORD_FIELD_LOCATOR = createNameAttributeSelector("input", "[name=\"password\"]");;
    private static String SUBMIT_BUTTON = createNameAttributeSelector("input", "[name=\"submit\"]");;

    public static void signin(@NotNull String email, @NotNull String password){
        email(email);
        password(password);
        click(SUBMIT_BUTTON);
    }

    private static void email(@NotNull String email) {
        enterField(EMAIL_FIELD_LOCATOR, email);
    }

    private static void password(@NotNull String password) {
        enterField(PASSWORD_FIELD_LOCATOR, password);
    }

    public static String getEmailFieldLocator() {
        return EMAIL_FIELD_LOCATOR;
    }

    public static String getPasswordFieldLocator() {
        return PASSWORD_FIELD_LOCATOR;
    }

}