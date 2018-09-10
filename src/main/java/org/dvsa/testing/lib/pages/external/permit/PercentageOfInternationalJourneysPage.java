package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.external.permit.enums.JourneyProportion;

public class PercentageOfInternationalJourneysPage extends BasePermitPage {

    private static String PROPORTION_TEMPLATE = "label:nth-of-type(%s) input[type=radio]";

    final public static String RESOURCE = "ecmt-international-journey/";

    public static void proportion(JourneyProportion proportion){
        scrollAndClick(String.format(PROPORTION_TEMPLATE, proportion.ordinal() + 1));
    }

}
