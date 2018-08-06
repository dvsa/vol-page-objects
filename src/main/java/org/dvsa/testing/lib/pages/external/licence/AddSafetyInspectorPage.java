package org.dvsa.testing.lib.pages.external.licence;

import activesupport.IllegalBrowserException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.exception.ElementDidNotAppearWithinSpecifiedTimeException;
import org.dvsa.testing.lib.pages.external.licence.enums.InspectorType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AddSafetyInspectorPage extends BasePage {
    // Selectors
    private static String INSPECTOR_TYPE_TEMPLATE = "fieldset > fieldset > label:nth-of-type(%d) input";
    private static String NAME_OF_INSPECTOR = "input[name='contactDetails[fao]']";

    private static String POSTCODE_SEARCH_FIELD = "input[name='address[searchPostcode][postcode]']";
    private static String FIND_ADDRESS_BUTTON = "button[name='address[searchPostcode][search]']";
    private static String ADDRESS_SELECTOR = "select[name='address[searchPostcode][addresses]']";

    // Behaviour
    public static void inspectorType(@NotNull InspectorType inspectorType) throws IllegalBrowserException {
        click(String.format(INSPECTOR_TYPE_TEMPLATE, inspectorType.ordinal() + 1));
    }

    public static void nameOfInspector(@NotNull String nameOfInspector) throws IllegalBrowserException {
        enterField(NAME_OF_INSPECTOR, nameOfInspector);
    }

    public static List<String> postcodeSearch(@NotNull String postcode) throws IllegalBrowserException, ElementDidNotAppearWithinSpecifiedTimeException {
        enterField(POSTCODE_SEARCH_FIELD, postcode);
        AddSafetyInspectorPage.findAddress();
        untilElementPresentWithin(ADDRESS_SELECTOR, 5000);
        return getListValues(ADDRESS_SELECTOR);
    }

    public static void findAddress() throws IllegalBrowserException {
        click(FIND_ADDRESS_BUTTON);
    }

}
