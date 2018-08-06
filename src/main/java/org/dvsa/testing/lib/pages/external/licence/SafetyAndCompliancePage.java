package org.dvsa.testing.lib.pages.external.licence;

import activesupport.IllegalBrowserException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.licence.enums.AnalysisMethod;
import org.jetbrains.annotations.NotNull;

public class SafetyAndCompliancePage extends BasePage {
    // Selectors
    private static String WEEKS_BEFORE_SAFETY_INSPECTION_ON_VEHICLES = "input[name='licence[safetyInsVehicles]']";
    private static String WEEKS_BEFORE_SAFETY_INSPECTION_ON_TRAILERS = "input[name='licence[safetyInsTrailers]']";

    private static String IS_INSPECTED_MORE_OFTEN_TEMPLATE = "fieldset:nth-of-type(1) > fieldset:nth-of-type(1) > label:nth-of-type(%d)";
    private static String TACHOGRAPH_ANALYSIS_METHOD = "fieldset:nth-of-type(1) > fieldset:nth-of-type(2) > label:nth-of-type(%d)";

    private static String MADE_ARRANGEMENTS_FOR_VEHICLES = "(//*/input[@name='application[safetyConfirmation]'])[2]";
    private static String MADE_ARRANGEMENTS_FOR_VEHICLES_SELECTED_LABEL = MADE_ARRANGEMENTS_FOR_VEHICLES + "/../../"
            + "label[contains(concat(' ',normalize-space(@class),' '),'selected')]";

    private static String SAVE_AND_CONTINUE_BUTTON = "button[name='form-actions[saveAndContinue]']";
    private static String SAVE_AND_RETURN_TO_OVERVIEW = "button[name='form-actions[save]']";

    // Behaviour
    public static void weeksBeforeSafetyInspectionOnVehicles(int numberOfWeeks) throws IllegalBrowserException {
        enterField(WEEKS_BEFORE_SAFETY_INSPECTION_ON_VEHICLES, String.valueOf(numberOfWeeks));
    }

    public static void weeksBeforeSafetyInspectionOnTrailers(int numberOfWeeks) throws IllegalBrowserException {
        enterField(WEEKS_BEFORE_SAFETY_INSPECTION_ON_TRAILERS, String.valueOf(numberOfWeeks));
    }

    public static void isInspectedMoreOften(boolean isInspectedMore) throws IllegalBrowserException {
        int position = isInspectedMore ? 1 : 2;
        click(String.format(IS_INSPECTED_MORE_OFTEN_TEMPLATE, position));
    }

    public static void tachographAnalysisMethod(@NotNull AnalysisMethod analysisMethod) throws IllegalBrowserException {
        click(String.format(TACHOGRAPH_ANALYSIS_METHOD, analysisMethod.ordinal() + 1));
    }

    public static void madeArrangementsForVehicles(boolean madeArrangements) throws IllegalBrowserException {
        if (madeArrangements && !isMadeArrangementsForVehiclesSelected()) {
            click(MADE_ARRANGEMENTS_FOR_VEHICLES, SelectorType.XPATH);
        } else if (madeArrangements == false && isMadeArrangementsForVehiclesSelected()) {
            click(MADE_ARRANGEMENTS_FOR_VEHICLES, SelectorType.XPATH);
        }
    }

    private static boolean isMadeArrangementsForVehiclesSelected() throws IllegalBrowserException {
        return isElementPresent(MADE_ARRANGEMENTS_FOR_VEHICLES_SELECTED_LABEL, SelectorType.XPATH);
    }

    public static void saveAndContinue() throws IllegalBrowserException {
        click(SAVE_AND_CONTINUE_BUTTON);
    }

    public static void saveAndReturnToOverview() throws IllegalBrowserException {
        click(SAVE_AND_RETURN_TO_OVERVIEW);
    }

}
