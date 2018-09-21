package org.dvsa.testing.lib.pages.external.permit;

import activesupport.string.Str;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.permit.enums.FeeSection;

public class FeeOverviewPage extends BasePermitPage {

    private static String TABLE_SECTION_TEMPLATE = "//h4[contains(text(), '%s')]/../../td[2]";

    private static String SUBMIT_AND_PAY = "#submit-accept-button";
    private static String OVERVIEW = ".ecmt-overview-return-link";

    public static void saveAndContinue() {
        scrollAndClick(SUBMIT_AND_PAY);
    }

    public static void overview(){
        scrollAndClick(OVERVIEW);
    }

    public static String getSectionValue(FeeSection section) {
        return getText(String.format(TABLE_SECTION_TEMPLATE, section.toString()), SelectorType.XPATH).trim();
    }

    public static String numberOfPermits() {
        return Str.find("\\d+(?= x £\\d+ \\(per permit\\))", getSectionValue(FeeSection.PermitsRequired));
    }

    public static String pricePerPermit() {
        return Str.find("(?<=\\d x £)\\d+", getSectionValue(FeeSection.PermitsRequired));
    }

}
