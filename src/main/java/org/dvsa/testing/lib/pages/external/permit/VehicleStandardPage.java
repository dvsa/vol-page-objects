package org.dvsa.testing.lib.pages.external.permit;

public class VehicleStandardPage extends BasePermitPage {

    private static String CONFIRM_COMPLIANCE = "#MeetsEuro6";
    private static String IS_COMPLIANT = "label.selected " + CONFIRM_COMPLIANCE;

    final public static String RESOURCE = "ecmt-euro6/";

    public static void isEuro6Compliant(boolean isCompliant) {
        if (isCompliant){
            if (isCheckboxNotSelected()) // Checks to see if the checkbox is already selected
                scrollAndClick(CONFIRM_COMPLIANCE);
        }  else {
            if (isCheckboxSelected())
                scrollAndClick(CONFIRM_COMPLIANCE);
        }
    }

    private static boolean isCheckboxSelected(){
        return isElementPresent(IS_COMPLIANT);
    }

    private static boolean isCheckboxNotSelected(){
        return !isCheckboxSelected();
    }

}
