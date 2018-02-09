package org.dvsa.testing.lib.pages.external.licence;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;

public class VehicleDetailsPage extends BasePage {
    // Selectors
    private static String SUBMIT_VEHICLE_DETAILS_TEMPLATE = "fieldset:nth-of-type(2) > fieldset label:nth-of-type(%d) input";

    private static String SAVE_AND_CONTINUE = "button[name='form-actions[saveAndContinue]']";
    private static String SAVE_AND_RETURN_TO_OVERVIEW = "button[name='form-actions[save]']";

    // Behaviour
    public static void submitVehicleDetails(boolean submit) throws UninitialisedDriverException {
        int position = submit ? 1 : 2;
        click(String.format(SUBMIT_VEHICLE_DETAILS_TEMPLATE, position));
    }

    public static void saveAndContinue() throws UninitialisedDriverException {
        click(SAVE_AND_CONTINUE);
    }

    public static void saveAndReturnToOverview() throws UninitialisedDriverException {
        click(SAVE_AND_RETURN_TO_OVERVIEW);
    }
}
