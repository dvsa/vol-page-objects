package org.dvsa.testing.lib.pages.internal;

import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Ints;
import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.AdminOption;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.exception.ElementDidNotAppearWithinSpecifiedTimeException;
import org.dvsa.testing.lib.pages.exception.IncorrectPageTitleException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomePage extends BasePage {
    // Selectors
    private static String ADMIN_BUTTON = "ul > li.admin__title";
    private static String ADMIN_MENU = "div.admin__menu";
    private static String ADMIN_MENU_OPTION_TEMPLATE = ADMIN_MENU + " ul.admin__submenu:nth-of-type(%d) > li:nth-of-type(%d) > a";

    // Attributes
    private static String PAGE_TITLE_TEXT = "Home";
    private static String RESOURCE_PATH = "";

    // Data Structures
    private static final int firstAdminList = 1;
    private static final int secondAdminList = 2;
    private static final ImmutableMap<AdminOption, List<Integer>> adminListMapper = new ImmutableMap.Builder<AdminOption, List<Integer>>()
            .put(AdminOption.SCANNING, Ints.asList(firstAdminList, 1))
            .put(AdminOption.PRINTING, Ints.asList(firstAdminList, 2))
            .put(AdminOption.PUBLIC_HOLIDAY, Ints.asList(firstAdminList, 3))
            .put(AdminOption.CONTINUATIONS, Ints.asList(firstAdminList, 4))
            .put(AdminOption.YOUR_ACCOUNT, Ints.asList(firstAdminList, 5))
            .put(AdminOption.SYSTEM_PARAMETERS, Ints.asList(firstAdminList, 6))
            .put(AdminOption.TASK_ALLOCATION_RULE, Ints.asList(firstAdminList, 7))
            .put(AdminOption.USER_MANAGEMENT, Ints.asList(secondAdminList, 1))
            .put(AdminOption.FINANCIAL_STANDING_RATES, Ints.asList(secondAdminList, 2))
            .put(AdminOption.PUBLICATIONS, Ints.asList(secondAdminList, 3))
            .put(AdminOption.PAYMENT_PROCESSING, Ints.asList(secondAdminList, 4))
            .put(AdminOption.REPORTS, Ints.asList(secondAdminList, 5))
            .put(AdminOption.SYSTEM_MESSAGES, Ints.asList(secondAdminList, 6))
            .put(AdminOption.DATA_RETENTION, Ints.asList(secondAdminList, 7))
            .build();



    // Behaviour
    public static void administratorButton() throws UninitialisedDriverException {
        click(ADMIN_BUTTON);
    }

    public static void administratorList(@NotNull AdminOption option) throws UninitialisedDriverException, ElementDidNotAppearWithinSpecifiedTimeException, IncorrectPageTitleException {
        int listPosition = adminListMapper.get(option).get(0);
        int listItemPosition = adminListMapper.get(option).get(1);

        administratorButton();
        untilElementPresentWithin(ADMIN_MENU, 1000);
        click(String.format(ADMIN_MENU_OPTION_TEMPLATE, listPosition, listItemPosition));
        untilNotExpectedPageTitle(PAGE_TITLE_TEXT);
    }

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
