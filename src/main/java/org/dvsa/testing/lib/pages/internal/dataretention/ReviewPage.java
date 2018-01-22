package org.dvsa.testing.lib.pages.internal.dataretention;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.enums.DataRetentionRule;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.exception.IncorrectPageTitleException;
import org.dvsa.testing.lib.pages.exception.UnableToFindDataRetentionRule;
import org.jetbrains.annotations.NotNull;

public class ReviewPage extends DataRetentionPage {
    // Selector
    private static String ROW_DESCRIPTION_LINK_TEMPLATE = "//*/td/a[text()[contains(.,'%s')]]";

    /**
     * This finds and selects the specified data retention rule, paginating through all the different pages if it is
     * unable to find it on the current page.
     * @param dataRetentionRule is the data retention rule we'd like to view the results of, the records that met its
     *                          criteria when the job was ran.
     * @throws UninitialisedDriverException
     * @throws IncorrectPageTitleException
     * @throws UnableToFindDataRetentionRule is thrown in the event that the rule specified as not been activated and
     * as a result was not found.
     */
    public static void rule(@NotNull DataRetentionRule dataRetentionRule) throws UninitialisedDriverException, IncorrectPageTitleException, UnableToFindDataRetentionRule {
        String selector = String.format(ROW_DESCRIPTION_LINK_TEMPLATE, dataRetentionRule.name());
        boolean foundDataRetentionRule = false;

        do {
            if (isElementPresent(selector, SelectorType.XPATH)) {
                foundDataRetentionRule = true;
                click(selector, SelectorType.XPATH);
            }
        } while (!isElementPresent(selector, SelectorType.XPATH) && isElementPresent(getNextPageButtonSelector(), SelectorType.XPATH));

        if (!foundDataRetentionRule) {
            throw new UnableToFindDataRetentionRule("Unable to find the data retention rule " + dataRetentionRule.name());
        }

        untilNotExpectedPageTitle(getPageTitleSelector(), getPageTitleText());
    }
}
