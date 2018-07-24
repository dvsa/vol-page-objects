package org.dvsa.testing.lib.pages.external.licence;

import activesupport.IllegalBrowserException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.Region;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.enums.TypeOfLicence;
import org.dvsa.testing.lib.pages.enums.TypeOfVehicle;
import org.dvsa.testing.lib.pages.exception.IncorrectPageTitleException;
import org.jetbrains.annotations.NotNull;

public class TypeOfLicencePage extends BasePage {
    // Selectors
    private static String LICENCE_OPTIONS_TEMPLATE = "fieldset > fieldset:nth-of-type(%d) label:nth-of-type(%d)";
    private static String INPUT_SELECTOR = " input[type='radio']";

    private static String REGION_BUTTON_TEMPLATE = String.format(LICENCE_OPTIONS_TEMPLATE, 1) + INPUT_SELECTOR;
    private static String VEHICLE_TYPE_TEMPLATE = String.format(LICENCE_OPTIONS_TEMPLATE, 2) + INPUT_SELECTOR;
    private static String TYPE_OF_LICENCE_TEMPLATE = String.format(LICENCE_OPTIONS_TEMPLATE, 3) + INPUT_SELECTOR;
    private static String SAVE_AND_CONTINUE_BUTTON = "//*/button[contains(text(),'Save and continue')]";
    private static String CANCEL_BUTTON = "//*/button[contains(text(),'Cancel')]";

    // Attributes
    private static String PAGE_TITLE_TEXT = "Type of licence";
    private static String RESOURCE_PATH = "application/create/";

    // Behaviour
    public static void region(@NotNull Region region) throws IllegalBrowserException {
        int position;

        switch (region) {
            case GREAT_BRITAIN:
                position = 1;
                break;
            case NORTHERN_IRELAND:
                position = 2;
                break;
            default:
                throw new IllegalArgumentException();
        }

        click(String.format(REGION_BUTTON_TEMPLATE, position), SelectorType.XPATH);
    }

    public static void typeOfVehicle(@NotNull TypeOfVehicle typeOfVehicle) throws IllegalBrowserException {
        int position;

        switch (typeOfVehicle) {
            case GOODS_VEHICLE:
                position = 1;
                break;
            case PUBLIC_SERVICES_VEHICLE:
                position = 2;
                break;
            default:
                throw new IllegalArgumentException();
        }

        click(String.format(VEHICLE_TYPE_TEMPLATE, position), SelectorType.XPATH);
    }

    public static void typeOfLicence(@NotNull TypeOfLicence typeOfLicence) throws IllegalBrowserException {
        int position;

        switch (typeOfLicence) {
            case RESTRICTED:
                position = 1;
                break;
            case STANDARD_NATIONAL:
                position = 2;
                break;
            case STANDARD_INTERNATIONAL:
                position = 3;
                break;
            default:
                throw new IllegalArgumentException();
        }

        click(String.format(TYPE_OF_LICENCE_TEMPLATE, position), SelectorType.XPATH);
    }

    public static void saveAndContinue() throws IllegalBrowserException {
        click(SAVE_AND_CONTINUE_BUTTON, SelectorType.XPATH);
    }

    public static void cancel() throws IllegalBrowserException {
        click(CANCEL_BUTTON, SelectorType.XPATH);
    }

}
