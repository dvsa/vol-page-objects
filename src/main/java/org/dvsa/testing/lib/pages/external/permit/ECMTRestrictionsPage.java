package org.dvsa.testing.lib.pages.external.permit;

public class ECMTRestrictionsPage extends BasePermitPage {

    private static String ECMT_RESTRICTED_COUNTRY_TEMPLATE = "#restricted-countries label:nth-of-type(%d) input[type='radio']";

    public static void deliverToRestrictedCountry(boolean answer) {
        int position = answer ? 1 : 2;
        scrollAndClick(String.format(ECMT_RESTRICTED_COUNTRY_TEMPLATE, position));
    }

}
