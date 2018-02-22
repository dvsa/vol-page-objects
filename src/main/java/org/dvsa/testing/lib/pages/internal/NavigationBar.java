package org.dvsa.testing.lib.pages.internal;

import activesupport.system.out.Output;
import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Ints;
import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.Action;
import org.dvsa.testing.lib.pages.enums.AdminOption;
import org.dvsa.testing.lib.pages.exception.ElementDidNotAppearWithinSpecifiedTimeException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NavigationBar extends BasePage {
    // Selectors
    private static String ADMIN_BUTTON = "ul > li.admin__title";
    private static String ADMIN_MENU = "div.admin__menu";
    private static String OPEN_ADMIN_MENU = ADMIN_MENU + ".active";
    private static String ADMIN_MENU_OPTION_TEMPLATE = ADMIN_MENU + " ul.admin__submenu:nth-of-type(%d) > li:nth-of-type(%d) > a";

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
    public static void adminPanel(@NotNull Action action) throws UninitialisedDriverException {
        switch (action) {
            case OPEN:
                openAdminPanel();
                break;
            case CLOSE:
                closeAdminPanel();
                break;
            default:
                throw new IllegalArgumentException(Output.printColoredLog("ERROR unsupported argument " + action + " used."));
        }
    }

    public static void openAdminPanel() throws UninitialisedDriverException {
        if (isAdminPanelClosed()) {
            administratorButton();
        }
    }

    public static void closeAdminPanel() throws UninitialisedDriverException {
        if (isAdminPanelOpen()) {
            administratorButton();
        }
    }

    private static boolean isAdminPanelOpen() throws UninitialisedDriverException {
        return isElementPresent(OPEN_ADMIN_MENU);
    }

    private static boolean isAdminPanelClosed() throws UninitialisedDriverException {
        return !isAdminPanelOpen();
    }

    public static void administratorButton() throws UninitialisedDriverException {
        click(ADMIN_BUTTON);
    }

    public static void administratorList(@NotNull AdminOption option) throws UninitialisedDriverException, ElementDidNotAppearWithinSpecifiedTimeException {
        int listPosition = adminListMapper.get(option).get(0);
        int listItemPosition = adminListMapper.get(option).get(1);

        if (isAdminPanelClosed()) {
            openAdminPanel();
        }

        untilElementPresentWithin(OPEN_ADMIN_MENU, 1000);
        click(String.format(ADMIN_MENU_OPTION_TEMPLATE, listPosition, listItemPosition));
    }
}
