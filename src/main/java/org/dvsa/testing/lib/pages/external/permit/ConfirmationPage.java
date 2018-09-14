package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class ConfirmationPage extends BasePage {

    private static String FINISH = "//a[contains(text(), 'Finish')]";
    private static String VIEWRECEIPT = "//a[contains(text(), 'View Receipt')]";
    private static String ACCEPT_AND_CONTINUE = "//*[@id='submit-accept-button']";

    final public static String RESOURCE = "/ecmt-submitted/";

    final public static String DASHBOARD = "permits";

    final public static String PAYMENTPAGE = "payment_receipt.html";

    public static void finishButton() {
        scrollAndClick(FINISH, SelectorType.XPATH);
    }

    public static void viewReceiptLink()
    {scrollAndClick(VIEWRECEIPT,SelectorType.XPATH);}

    public static void acceptAndContinueButton() {
        scrollAndClick(ACCEPT_AND_CONTINUE,SelectorType.XPATH);
    }
}
