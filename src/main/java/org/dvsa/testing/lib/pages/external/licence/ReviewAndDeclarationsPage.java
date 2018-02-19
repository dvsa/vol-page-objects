package org.dvsa.testing.lib.pages.external.licence;

import org.dvsa.testing.lib.Util;
import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.external.licence.enums.SigningMethod;
import org.jetbrains.annotations.NotNull;

public class ReviewAndDeclarationsPage extends LicenceBasePage  {
    // Selectors
    private static String SIGNING_METHOD_TEMPLATE = "fieldset[data-group='declarationsAndUndertakings'] > fieldset label:nth-of-type(%d) input";
    private static String APPLY_FOR_INTERIM_TEMPLATE = "fieldset[data-group='interim'] > fieldset label:nth-of-type(%d) input";

    private static String PAY_AND_SUBMIT_BUTTON = "button[name='form-actions[submitAndPay]']";
    private static String CHANGE_SOMETHING_ELSE_BUTTON = "button[name='form-actions[change]']";

    public static void signingMethod(@NotNull SigningMethod method) throws UninitialisedDriverException {
        click(String.format(SIGNING_METHOD_TEMPLATE, method.ordinal() + 1));
    }

    public static void interim(boolean apply) throws UninitialisedDriverException {
        int position = Util.radioPosition(apply);
        click(String.format(APPLY_FOR_INTERIM_TEMPLATE, position));
    }

    public static void payAndSubmit() throws UninitialisedDriverException {
        click(PAY_AND_SUBMIT_BUTTON);
    }

    public static void changeSomethingElse() throws UninitialisedDriverException {
        click(CHANGE_SOMETHING_ELSE_BUTTON);
    }

}