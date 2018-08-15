package org.dvsa.testing.lib.pages.external.permit;

public class DeclarationPage extends BasePermitPage {

    private static String DECLARATION = "input[type='checkbox']#declaration";
    private static String ACCEPT_AND_CONTINUE = "#submit-accept-button";

    private static String HAS_DECLARED = "label.selected " + DECLARATION;

    public static void declare(boolean declaration){
        if (declaration){
            if (hasNotDeclared())
                scrollAndClick(DECLARATION);
        } else {
            if (hasDeclared())
                scrollAndClick(DECLARATION);
        }
    }

    public static void saveAndContinue(){
        scrollAndClick(ACCEPT_AND_CONTINUE);
    }

    private static boolean hasDeclared(){
        return isElementPresent(HAS_DECLARED);
    }

    private static boolean hasNotDeclared(){
        return !hasDeclared();
    }

}
