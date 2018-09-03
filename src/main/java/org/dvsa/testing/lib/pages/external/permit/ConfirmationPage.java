package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class ConfirmationPage extends BasePage {

    private static String FINISH = "//a[contains(text(), 'Finish')]";
    private static String VIEWRECEIPT = "//a[contains(text(), 'View Receipt')]";

    final public static String RESOURCE = "/ecmt-submitted";

    final public static String DASHBOARD = "/permits";

    final public static String PAYMENTPAGE = "/payment_receipt.html";

    public static void finishButton() {
        scrollAndClick(FINISH, SelectorType.XPATH);
    }

    public static void ViewReceiptLink()
    {scrollAndClick(VIEWRECEIPT,SelectorType.XPATH);}

}
