package org.dvsa.testing.lib.pages.external.permit;

public class FeeOverviewPage extends BasePermitPage {

    private static String REFERENCE_NUMBER = "tbody tr:first-child td:nth-of-type(2)";

    private static String SUBMIT_AND_PAY = "#submit-accept-button";
    private static String OVERVIEW = ".ecmt-overview-return-link";

    public static void saveAndContinue() {
        scrollAndClick(SUBMIT_AND_PAY);
    }

    public static void overview(){
        scrollAndClick(OVERVIEW);
    }

    public static String reference() {
        return getText(REFERENCE_NUMBER);
    }

}
