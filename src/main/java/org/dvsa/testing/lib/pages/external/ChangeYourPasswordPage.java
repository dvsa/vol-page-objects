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

    public void setCurrentPasswordField(@NotNull String currentPasswordField){
        enterField(CURRENT_PASSWORD_FIELD, currentPasswordField);
    }

    public void setNewPasswordField(@NotNull String newPasswordField){
        enterField(NEW_PASSWORD_FIELD, newPasswordField);
    }

    public void setConfirmPasswordField(@NotNull String confirmPasswordField){
        enterField(CONFIRM_PASSWORD_FIELD, confirmPasswordField);
    }

    public void submit(){
        click(SUBMIT_BUTTON);
    }

    public boolean isCurrentPage(){
        return isCurrentPage(PAGE_TITLE, PAGE_TITLE_TEXT);
    }
}
