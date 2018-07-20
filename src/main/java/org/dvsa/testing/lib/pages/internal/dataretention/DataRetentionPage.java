package org.dvsa.testing.lib.pages.internal.dataretention;

import activesupport.MissingDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.enums.dataretention.DataRetentionRule;
import org.dvsa.testing.lib.pages.exception.IncorrectPageTitleException;
import org.dvsa.testing.lib.pages.exception.UnableToFindDataRetentionRule;
import org.jetbrains.annotations.NotNull;

public class DataRetentionPage extends BasePage {
    // Selectors
    private static String REVIEW = "//*/a[contains(text(), 'Review')]";
    private static String EXPORT = "//*/a[contains(text(), 'Export')]";
    private static String RULE_ADMIN = "//*/a[contains(text(), 'Rule admin')]";

    private static String NEXT_BUTTON = "//*/a[text()[contains(.,'Next')]]";
    private static String EMPTY_TABLE = "//*/p[text()[contains(.,'The table is empty')]]";

    private static String RULE_DESCRIPTION_LINK_TEMPLATE = "//*/td/a[text()[contains(.,'%s')]]";

    // Attributes
    private static String PAGE_TITLE_SELECTOR = "h4";
    private static String PAGE_TITLE_TEXT = "Data retention";

    // Behaviour
    public static boolean isEmpty() throws MissingDriverException {
        return isElementPresent(EMPTY_TABLE, SelectorType.XPATH);
    }

    public static void review() throws MissingDriverException {
        click(REVIEW, SelectorType.XPATH);
    }

    public static void export() throws MissingDriverException {
        click(EXPORT, SelectorType.XPATH);
    }

    public static void ruleAdmin() throws MissingDriverException {
        click(RULE_ADMIN, SelectorType.XPATH);
    }

    public static String getPageTitleSelector() {
        return PAGE_TITLE_SELECTOR;
    }

    public static String getPageTitleText() {
        return PAGE_TITLE_TEXT;
    }

    public static String getNextPageButtonSelector() {
        return NEXT_BUTTON;
    }

    /**
     * This finds and selects the specified data retention rule, paginating through all the different pages if it is
     * unable to find it on the current page.
     * @param dataRetentionRule is the data retention rule we'd like to view the results of, the records that met its
     *                          criteria when the job was ran.
     * @throws MissingDriverException
     * @throws UnableToFindDataRetentionRule is thrown in the event that the rule specified as not been activated and
     * as a result was not found.
     */
    public static void selectRule(@NotNull DataRetentionRule dataRetentionRule) throws MissingDriverException, UnableToFindDataRetentionRule {
        String ruleSelector = String.format(RULE_DESCRIPTION_LINK_TEMPLATE, dataRetentionRule.toString());

        pagenateToRule(ruleSelector, SelectorType.XPATH, 2);

        scrollTo(ruleSelector, SelectorType.XPATH);
        click(ruleSelector, SelectorType.XPATH);
    }

    public static void selectRule(@NotNull String dataRetentionRule) throws MissingDriverException, UnableToFindDataRetentionRule {
        DataRetentionRule rule = DataRetentionRule.getEnum(dataRetentionRule);

        selectRule(rule);
    }

    protected static void pagenateToRule(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) throws MissingDriverException, UnableToFindDataRetentionRule {
        boolean foundDataRetentionRule = false;

        do {
            if (isInDOM(selector, selectorType, seconds)) {
                foundDataRetentionRule = true;
                break;
            } else if (isInDOM(NEXT_BUTTON, SelectorType.XPATH)){
                click(NEXT_BUTTON, SelectorType.XPATH);
            } else if (isNotInDOM(NEXT_BUTTON, SelectorType.XPATH)) {
                break;
            }
        } while (true);

        if (!foundDataRetentionRule) {
            throw new UnableToFindDataRetentionRule("Unable to find the element with the selector '"
                    + selector
                    + "' after pagenation"
            );
        }

    }

}
