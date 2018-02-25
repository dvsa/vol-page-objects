package org.dvsa.testing.lib.pages.internal.dataretention;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class DataRetentionPage extends BasePage {
    // Selectors
    private static String REVIEW = "//*/a[contains(text(), 'Review')]";
    private static String EXPORT = "//*/a[contains(text(), 'Export')]";
    private static String RULE_ADMIN = "//*/a[contains(text(), 'Rule admin')]";

    private static String NEXT_PAGE_BUTTON = "//*[@class='pagination__item ']/a[text()[contains(.,'Next')]]";
    private static String EMPTY_TABLE = "//*/p[text()[contains(.,'The table is empty')]]";

    // Attributes
    private static String PAGE_TITLE_SELECTOR = "h4";
    private static String PAGE_TITLE_TEXT = "Data retention";

    // Behaviour
    public static boolean isEmpty() throws UninitialisedDriverException {
        return isElementPresent(EMPTY_TABLE, SelectorType.XPATH);
    }

    public static void review() throws UninitialisedDriverException {
        click(REVIEW, SelectorType.XPATH);
    }

    public static void export() throws UninitialisedDriverException {
        click(EXPORT, SelectorType.XPATH);
    }

    public static void ruleAdmin() throws UninitialisedDriverException {
        click(RULE_ADMIN, SelectorType.XPATH);
    }

    public static String getPageTitleSelector() {
        return PAGE_TITLE_SELECTOR;
    }

    public static String getPageTitleText() {
        return PAGE_TITLE_TEXT;
    }

    public static String getNextPageButtonSelector() {
        return NEXT_PAGE_BUTTON;
    }
}
