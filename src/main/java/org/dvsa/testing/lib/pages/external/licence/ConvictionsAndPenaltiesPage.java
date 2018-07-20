package org.dvsa.testing.lib.pages.external.licence;

import org.dvsa.testing.lib.Util;
import activesupport.MissingDriverException;

public class ConvictionsAndPenaltiesPage extends LicenceBasePage {
    // Selectors
    private static String QUESTION_1_TEMPLATE = "fieldset > label:nth-of-type(%d)";

    public static void Q1(boolean answer) throws MissingDriverException {
        int position = Util.radioPosition(answer);
        click(String.format(QUESTION_1_TEMPLATE, position));
    }
}
