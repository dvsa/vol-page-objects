package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.browser.Browser;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.permit.enums.JourneyProportion;

import java.util.concurrent.TimeUnit;

public class PercentageOfInternationalJourneysPage extends BasePermitPage {

    private static String PROPORTION_TEMPLATE = "label:nth-of-type(%s) input[type=radio]";

    final public static String RESOURCE = "ecmt-trips/";

    public static void proportion(JourneyProportion proportion){
        Browser.Wait.untilUrlIs(RESOURCE, TimeUnit.SECONDS, BasePermitPage.WAIT_TIME_SECONDS);
        String selector = String.format(PROPORTION_TEMPLATE, proportion.ordinal() + 1);

        untilElementIsPresent(selector, SelectorType.CSS, BasePermitPage.WAIT_TIME_SECONDS, TimeUnit.SECONDS);
        scrollAndClick(String.format(PROPORTION_TEMPLATE, proportion.ordinal() + 1));
    }

}
