package org.dvsa.testing.lib.pages.external.licence;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.jetbrains.annotations.NotNull;

public class OperatingCentresAndAuthorisationPage extends BasePage {
    // Selectors
    private static String ADD_OPERATING_CENTRE = "button[name='table[action]']";

    private static String DESIRED_NUMBER_OF_VEHICLES_AUTHORISED_ON_LICENCE = "input[name='data[totAuthVehicles]']";
    private static String DESIRED_NUMBER_OF_TRAILERS_AUTHORISED_ON_LICENCE = "input[name='data[totAuthTrailers]']";

    private static String SAVE_AND_CONTINUE_BUTTON = "button[name='form-actions[saveAndContinue]']";
    private static String SAVE_AND_RETURN_TO_OVERVIEW_BUTTON = "button[name='form-actions[saveAndContinue]']";

    // Attributes
    private static String PAGE_TITLE_TEXT = "Operating centres and authorisation";
    private static String RESOURCE_PATH = "application/\\d{7}/operating-centres/";

    // Behaviour
    public static void addOperatingCentre() throws UninitialisedDriverException {
        click(ADD_OPERATING_CENTRE);
    }

    public static void desiredNumberOfVehiclesAuthorisedOnLicence(@NotNull int numberOfVehicles) throws UninitialisedDriverException {
        enterField(DESIRED_NUMBER_OF_VEHICLES_AUTHORISED_ON_LICENCE, String.valueOf(numberOfVehicles));
    }

    public static void desiredNumberOfTrailersAuthorisedOnLicence(@NotNull int numberOfTrailers) throws UninitialisedDriverException {
        enterField(DESIRED_NUMBER_OF_TRAILERS_AUTHORISED_ON_LICENCE, String.valueOf(numberOfTrailers));
    }

    public static void saveAndContinue() throws UninitialisedDriverException {
        click(SAVE_AND_CONTINUE_BUTTON);
    }

    public static void saveAndReturnToOverview() throws UninitialisedDriverException {
        click(SAVE_AND_RETURN_TO_OVERVIEW_BUTTON);
    }
}
