package org.dvsa.testing.lib.pages.internal.dataretention;

import activesupport.MissingDriverException;

public class ReviewPage extends DataRetentionPage {
    // Attributes
    private static String TABLE_HEADER = "Data Retention rules";

    public static void untilOnPage() throws MissingDriverException {
        untilExpectedTextInElement("h3", TABLE_HEADER, 3);
    }

}
