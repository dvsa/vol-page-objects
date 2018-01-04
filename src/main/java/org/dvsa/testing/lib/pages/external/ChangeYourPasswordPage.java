package org.dvsa.testing.lib.pages.external;

import org.dvsa.testing.lib.pages.BasePage;
import org.jetbrains.annotations.NotNull;

public class ChangeYourPasswordPage extends BasePage{

    private static String CURRENT_PASSWORD_FIELD = nameAttribute("input", "oldPassword");
    private static String NEW_PASSWORD_FIELD = nameAttribute("input", "newPassword");
    private static String CONFIRM_PASSWORD_FIELD = nameAttribute("input", "confirmPassword");
    private static String SUBMIT_BUTTON = nameAttribute("input", "submit");

    // Page Attributes
    private static String PAGE_TITLE_TEXT = "Change your password";

    public static void setCurrentPasswordField(@NotNull String currentPasswordField){
        enterField(CURRENT_PASSWORD_FIELD, currentPasswordField);
    }

    public static void setNewPasswordField(@NotNull String newPasswordField){
        enterField(NEW_PASSWORD_FIELD, newPasswordField);
    }

    public static void setConfirmPasswordField(@NotNull String confirmPasswordField){
        enterField(CONFIRM_PASSWORD_FIELD, confirmPasswordField);
    }

    public static void submit(){
        click(SUBMIT_BUTTON);
    }

    public static boolean isCurrentPage(){
        return isCurrentPage(PAGE_TITLE, PAGE_TITLE_TEXT);
    }
}
