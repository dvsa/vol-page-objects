package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.permit.enums.RestrictedCountry;

public class RestrictedCountriesPage extends BasePermitPage {

    private static String RESTRICTED_COUNTRY_TEMPLATE = "#restricted-countries label:nth-of-type(%d) input[type='radio']";
    private static String COUNTRY_TEMPLATE = "//label[contains(text(), '%s')]/input";

    public static void deliverToRestrictedCountry(boolean answer) {
        int position = answer ? 1 : 2;
        scrollAndClick(String.format(RESTRICTED_COUNTRY_TEMPLATE, position));
    }

    public static void countries(RestrictedCountry... countries) {
        deliverToRestrictedCountry(true);

        for(RestrictedCountry country : countries) {
            scrollAndClick(String.format(COUNTRY_TEMPLATE, country.toString()), SelectorType.XPATH);
        }
    }

}
