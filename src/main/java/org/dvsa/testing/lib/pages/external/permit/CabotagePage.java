package org.dvsa.testing.lib.pages.external.permit;

public class CabotagePage extends BasePermitPage {

    private static String CABOTAGE = "#WontCabotage";
    private static String NO_CABOTAGE = "label.selected " + CABOTAGE;

    final public static String RESOURCE = "ecmt-cabotage/";

    public static void wontCarryCabotage(boolean wontCarryCabotage) {
        if (wontCarryCabotage){
            if (isCheckboxNotSelected()){
                scrollAndClick(CABOTAGE);
            }
        } else {
            if (isCheckboxSelected()){
                scrollAndClick(CABOTAGE);
            }
        }
    }

    private static boolean isCheckboxSelected() {
        return isElementPresent(NO_CABOTAGE);
    }

    private static boolean isCheckboxNotSelected() {
        return !isCheckboxSelected();
    }

}
