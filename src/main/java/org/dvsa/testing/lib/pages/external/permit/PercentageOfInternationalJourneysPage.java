package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.external.permit.enums.JourneyProportion;

public class PercentageOfInternationalJourneysPage extends BasePermitPage {

    private static String PROPORTION_TEMPLATE = "label:nth-of-type(%s) input[type=radio]";

    public static void proportion(JourneyProportion proportion){
        scrollAndClick(String.format(PROPORTION_TEMPLATE, proportion.toString()));
    }

}
