package org.dvsa.testing.lib.pages.external.permit;

public class CheckAnswersPage extends BasePermitPage {

    private static String ACCEPT_AND_CONTINUE = "#submit-accept-button";

    public static void saveAndContinue(){
        scrollAndClick(ACCEPT_AND_CONTINUE);
    }

}
