package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.permit.enums.PermitSection;
import org.jetbrains.annotations.NotNull;

public class OverviewPage extends BasePermitPage {

    private static String SECTION_TEMPLATE = "//a/span[contains(text(), '%s')]/..";

    final public static String RESOURCE = "/permit-overview";

    public static void section(@NotNull PermitSection section){
        scrollAndClick(String.format(SECTION_TEMPLATE, section.toString()), SelectorType.XPATH);
    }

}
