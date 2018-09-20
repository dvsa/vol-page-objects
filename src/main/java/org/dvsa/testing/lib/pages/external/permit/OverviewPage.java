package org.dvsa.testing.lib.pages.external.permit;

import activesupport.string.Str;
import com.amazonaws.util.StringUtils;
import org.dvsa.testing.lib.enums.PermitStatus;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.permit.enums.PermitSection;
import org.jetbrains.annotations.NotNull;

public class OverviewPage extends BasePermitPage {

    private static String SECTION_TEMPLATE = "//a/span[contains(text(), '%s')]/..";
    private static String ACTIVE_SECTION_STATUS_TEMPLATE = "//span[contains(text(), '%s')]/following-sibling::span";
    private static String DISABLED_SECTION_STATUS_TEMPLATE = "//div[contains(text(), '%s')]/span";

    private static String APPLICATION_REFERENCE = "h3.page-header__subtitle";

    private static String PROGRESS_PANEL = ".sidebar";
    private static String PROGRESS_PANEL_COMPLETE_SECTIONS = PROGRESS_PANEL + " p:nth-of-type(1) b";
    private static String PROGRESS_PANEL_APPLICATION_FEE = PROGRESS_PANEL + " p:nth-of-type(3)";

    final public static String RESOURCE = "/application-overview";

    public static void section(@NotNull PermitSection section){
        scrollAndClick(String.format(SECTION_TEMPLATE, section.toString()), SelectorType.XPATH);
    }

    public static boolean checkStatus(PermitSection section, PermitStatus status){
        String selector = isActive(section) ? ACTIVE_SECTION_STATUS_TEMPLATE : DISABLED_SECTION_STATUS_TEMPLATE;
        String sectionStatus = getText(String.format(selector, section.toString()), SelectorType.XPATH);
        return StringUtils.trim(sectionStatus).toLowerCase().contains(StringUtils.trim(status.toString()).toLowerCase());
    }

    public static PermitStatus statusOfSection(@NotNull PermitSection section) {
        String selector = isActive(section) ? ACTIVE_SECTION_STATUS_TEMPLATE : DISABLED_SECTION_STATUS_TEMPLATE;
        String status = getText(String.format(selector, section.toString()), SelectorType.XPATH);
        return PermitStatus.getEnum(status);
    }

    public static boolean isActive(@NotNull PermitSection section){
        return isElementPresent(String.format(ACTIVE_SECTION_STATUS_TEMPLATE, section.toString()), SelectorType.XPATH);
    }

    public static int numOfCompleteSections() {
        String subject = getText(PROGRESS_PANEL_COMPLETE_SECTIONS);
        return Integer.parseInt(Str.find("\\d(?= of \\d{1,2} complete)", subject));
    }

    public static int applicationFee() {
        String subject = getText(PROGRESS_PANEL_APPLICATION_FEE);
        return Integer.parseInt(Str.find("(?<=Total application fee: £)(\\d+)", subject));
    }

    public static String reference() {
        return getText(APPLICATION_REFERENCE);
    }

    public static String licenceNumber() {
        return Str.find("\\w{2}\\d{7}(?= / \\d+)", reference());
    }

    public static String permitNumber() {
        return Str.find("(?<=\\w{2}\\d{7} / )\\d+", reference());
    }

}
