package org.dvsa.testing.lib.pages.internal.dataretention;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;

public class ReviewPage extends DataRetentionPage {
    // Attributes
    private static String TABLE_HEADER = "Data Retention rules";

    public static void untilOnPage() throws UninitialisedDriverException {
        untilExpectedTextInElement("h3", TABLE_HEADER, 3);
    }

}
