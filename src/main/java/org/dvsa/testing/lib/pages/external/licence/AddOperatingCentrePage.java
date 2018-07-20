package org.dvsa.testing.lib.pages.external.licence;

import activesupport.MissingDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.licence.enums.DeliveryMethod;
import org.jetbrains.annotations.NotNull;

public class AddOperatingCentrePage extends BasePage {
    // Selector
    private static String POST_CODE_SEARCH = "input[name='address[searchPostcode][postcode]']";
    private static String FIND_ADDRESS_BUTTON = "button[name='address[searchPostcode][search]']";

    private static String TOTAL_NUMBER_OF_VEHICLES = "input[name='data[noOfVehiclesRequired]']";
    private static String TOTAL_NUMBER_OF_TRAILERS = "input[name='data[noOfTrailersRequired]']";

    private static String SELECTED_ACKNOWLEDGEMENT_LABEL = "fieldset:nth-of-type(2) > fieldset label.selected";
    private static String ACKNOWLEDGEMENT = "input[name='data[permission][permission]']";

    private static String ADVERT_CHOICE_TEMPLATE = "//fieldset[@class='radio-button__fieldset']/div[@class='radio-button__container'][%d]/input";

    private static String SAVE_BUTTON = "button[name='form-actions[submit]']";
    private static String CANCEL_BUTTON = "button[name='form-actions[cancel]']";
    // Attributes

    // Behaviour
    public static void findAddress(@NotNull String postCode) throws MissingDriverException {
        postCodeSearch(postCode);
        findAddress();
    }

    public static void postCodeSearch(@NotNull String postCode) throws MissingDriverException {
        enterField(POST_CODE_SEARCH, postCode);
    }

    public static void findAddress() throws MissingDriverException {
        click(FIND_ADDRESS_BUTTON);
    }

    public static void vehicles(int numberOfVehicles) throws MissingDriverException {
        enterField(TOTAL_NUMBER_OF_VEHICLES, String.valueOf(numberOfVehicles));
    }

    public static void trailers(int numberOfTrailers) throws MissingDriverException {
        enterField(TOTAL_NUMBER_OF_TRAILERS, String.valueOf(numberOfTrailers));
    }

    public static void acknowledgement(boolean acknowledge) throws MissingDriverException {
        if (acknowledge && !acknowledgementIsSelected()) {
            click(ACKNOWLEDGEMENT);
        } else if (acknowledgementIsSelected()) {
            click(ACKNOWLEDGEMENT);
        }
    }

    private static boolean acknowledgementIsSelected() throws MissingDriverException {
        return isElementPresent(SELECTED_ACKNOWLEDGEMENT_LABEL);
    }

    public static void advertSubmission(@NotNull DeliveryMethod advert) throws MissingDriverException {
        click(String.format(ADVERT_CHOICE_TEMPLATE, advert.ordinal()), SelectorType.XPATH);
    }

    public static void save() throws MissingDriverException {
        click(SAVE_BUTTON);
    }

    public static void cancel() throws MissingDriverException {
        click(CANCEL_BUTTON);
    }
}
