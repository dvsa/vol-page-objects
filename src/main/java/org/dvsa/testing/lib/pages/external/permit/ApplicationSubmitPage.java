package org.dvsa.testing.lib.pages.external.permit;

import activesupport.string.Str;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.jetbrains.annotations.NotNull;

public class ApplicationSubmitPage extends BasePage {

    private static String FINISH = "//a[contains(text(), 'Finish')]";

    private static String REFERENCE_NUMBER = ".heading-medium";

    public static void finish(){
        scrollAndClick(FINISH, SelectorType.XPATH);
    }

    public static String getLicenceNumber(){
        return getReferenceNumberSection("\\w{2}\\d{7}");
    }

    public static String getPermitApplicationNumber(){
        return getReferenceNumberSection("(?<=\\w{2}\\d{7} / )\\d+");
    }

    public static String getReferenceNumberSection(@NotNull String regex){
        return Str.find(regex, getReferenceNumber());
    }

    public static String getReferenceNumber(){
        return getText(REFERENCE_NUMBER);
    }

}
