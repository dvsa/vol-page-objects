package org.dvsa.testing.lib.pages.internal.dataretention;

import activesupport.IllegalBrowserException;

public class ReviewPage extends DataRetentionPage {
    // Attributes
    private static String TABLE_HEADER = "Data Retention rules";

    public static void untilOnPage() throws IllegalBrowserException {
        untilExpectedTextInElement("h3", TABLE_HEADER, 3);
    }

}
