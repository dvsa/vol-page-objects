package org.dvsa.testing.lib.pages.internal;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class SearchNavBar extends NavigationBar {
    private static String SEARCH_LINK_TEMPLATE = ".horizontal-navigation li:nth-of-type(%d)";
    private static String LICENCE = String.format(SEARCH_LINK_TEMPLATE, 1);
    private static String APPLICATIONS = String.format(SEARCH_LINK_TEMPLATE, 2);
    private static String CASE = String.format(SEARCH_LINK_TEMPLATE, 3);
    private static String PSVDISC = String.format(SEARCH_LINK_TEMPLATE, 4);
    private static String VEHICLE = String.format(SEARCH_LINK_TEMPLATE, 5);
    private static String ADDRESS = String.format(SEARCH_LINK_TEMPLATE, 6);
    private static String BUSREGISTRATIONS = String.format(SEARCH_LINK_TEMPLATE, 7);
    private static String PEOPLE = String.format(SEARCH_LINK_TEMPLATE, 8);
    private static String USERS = String.format(SEARCH_LINK_TEMPLATE, 9);
    private static String PUBLICATION = String.format(SEARCH_LINK_TEMPLATE, 10);
    private static String IRFO =  String.format(SEARCH_LINK_TEMPLATE, 11);
    private static String SEARCH = nameAttribute("input", "search");
    private static String TEMP_LICENCE = "//*/a[contains(text(), '1014729')]";
    private static String SEARCH_BUTTON = nameAttribute("input", "submit");

    public static void search(String search) throws UninitialisedDriverException {
        enterField(SEARCH, search);
        click(SEARCH_BUTTON);
    }



    public static void licence() throws UninitialisedDriverException {
        click(LICENCE);
    }

    public static void applications() throws UninitialisedDriverException {
        click(APPLICATIONS);

    }

    public static void cases() throws UninitialisedDriverException {
        click(CASE);
    }

    public static void psvdisc() throws UninitialisedDriverException {
        click(PSVDISC);
    }

    public static void vehicle() throws UninitialisedDriverException {
        click(VEHICLE);
    }

    public static void address() throws UninitialisedDriverException {
        click(ADDRESS);
    }

    public static void busRegistrations() throws UninitialisedDriverException {
        click(BUSREGISTRATIONS);
    }

    public static void people() throws UninitialisedDriverException {
        click(PEOPLE);
    }

    public static void users() throws UninitialisedDriverException {
        click(USERS);
    }

    public static void publication() throws UninitialisedDriverException {
        click(PUBLICATION);
    }

    public static void irfo() throws UninitialisedDriverException {
        click(IRFO);
    }

    public static void TEMP_LICENCE() throws UninitialisedDriverException{
        click(TEMP_LICENCE, SelectorType.XPATH);
    }

}
