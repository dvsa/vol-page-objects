package org.dvsa.testing.lib.pages.external.permit;

import com.amazonaws.util.StringUtils;
import org.dvsa.testing.lib.enums.PermitStatus;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.permit.enums.PermitSection;
import org.jetbrains.annotations.NotNull;

public class OverviewPage extends BasePermitPage {

    private static String SECTION_TEMPLATE = "//a/span[contains(text(), '%s')]/..";

    final public static String RESOURCE = "/permit-overview";
    private static String OVERVIEWPAGE_STATUS_TEMPLATE = "//span[contains(text(), '%s')]/following-sibling::span";

    public static void section(@NotNull PermitSection section){
        scrollAndClick(String.format(SECTION_TEMPLATE, section.toString()), SelectorType.XPATH);
    }

    public static boolean checkStatus(PermitSection section, PermitStatus status){
        System.out.println(OVERVIEWPAGE_STATUS_TEMPLATE.toString());
        String sectionStatus = getText(String.format(OVERVIEWPAGE_STATUS_TEMPLATE, section.toString()), SelectorType.XPATH);
        System.out.println(sectionStatus);
        return StringUtils.trim(sectionStatus).toLowerCase().contains(StringUtils.trim(status.toString()).toLowerCase());
    }

}
