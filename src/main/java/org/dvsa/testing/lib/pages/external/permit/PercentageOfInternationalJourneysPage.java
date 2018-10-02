package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.browser.Browser;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.permit.enums.JourneyProportion;

import java.util.concurrent.TimeUnit;

public class PercentageOfInternationalJourneysPage extends BasePermitPage {

    private static String PROPORTION_TEMPLATE = "label:nth-of-type(%s) input[type=radio]";

    final public static String RESOURCE = "ecmt-international-journey/";

    private static String INTENSITY_MESSAGE = "You have stated a high percentage of international journeys for this licence. Please check the details are correct. We may contact you to verify this information";

    public static void proportion(JourneyProportion proportion){
        Browser.Wait.untilUrlPathIs(RESOURCE, TimeUnit.SECONDS, BasePermitPage.WAIT_TIME_SECONDS);
        String selector = String.format(PROPORTION_TEMPLATE, proportion.ordinal() + 1);

        untilElementIsPresent(selector, SelectorType.CSS, BasePermitPage.WAIT_TIME_SECONDS, TimeUnit.SECONDS);
        scrollAndClick(String.format(PROPORTION_TEMPLATE, proportion.ordinal() + 1));
    }

    public static boolean hasIntensityMessage() {
        return isTextPresent(INTENSITY_MESSAGE);
    }

}
