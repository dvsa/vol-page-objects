package org.dvsa.testing.lib.pages.external.permit;

public class VehicleStandardPage extends BasePermitPage {

    private static String CONFIRM_COMPLIANCE = "#MeetsEuro6";
    private static String IS_COMPLIANT = "label.selected " + CONFIRM_COMPLIANCE;

    public static void isEuro6Compliant(boolean isCompliant) {
        if (isCompliant){
            if (isElementNotPresent(IS_COMPLIANT)) // Checks to see if the checkbox is already selected
                scrollAndClick(CONFIRM_COMPLIANCE);
        }  else {
            if (isElementPresent(IS_COMPLIANT))
                scrollAndClick(CONFIRM_COMPLIANCE);
        }
    }

}
