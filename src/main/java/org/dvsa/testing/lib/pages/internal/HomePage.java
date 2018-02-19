package org.dvsa.testing.lib.pages.internal;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.exception.IncorrectPageTitleException;

public class HomePage extends BasePage {

    // Attributes
    private static String PAGE_TITLE_TEXT = "Home";

    public static void untilExpectedPageTitle() throws IncorrectPageTitleException, UninitialisedDriverException {
        untilExpectedPageTitle(PAGE_TITLE_TEXT);
    }

    public static void untilExpectedPageTitle(long horizonMilliseconds) throws IncorrectPageTitleException, UninitialisedDriverException {
        untilExpectedPageTitle(PAGE_TITLE_TEXT, horizonMilliseconds);
    }

    public static boolean isExpectedPageTitle() throws UninitialisedDriverException {
        return BasePage.isExpectedPageTitle(PAGE_TITLE_TEXT);
    }

    public static boolean isExpectedPageTitle(long horizonMilliseconds) throws UninitialisedDriverException {
        return BasePage.isExpectedPageTitle(PAGE_TITLE_TEXT, horizonMilliseconds);
    }

    public static boolean isNotExpectedPageTile() throws UninitialisedDriverException {
        return BasePage.isNotExpectedPageTitle(PAGE_TITLE_TEXT);
    }

    public static boolean isNotExpectedPageTile(long horizonMilliseconds) throws UninitialisedDriverException {
        return BasePage.isNotExpectedPageTitle(PAGE_TITLE_TEXT, horizonMilliseconds);
    }

}
