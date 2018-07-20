package org.dvsa.testing.lib.pages.internal;

import activesupport.MissingDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

import static org.junit.Assert.*;

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
    private static String IRFO = String.format(SEARCH_LINK_TEMPLATE, 11);
    private static String SEARCH = nameAttribute("input", "search");
    private static String NO_RESULTS_SEARCH = "//*[contains(text(), 'There were no results for your search')]";
    private static String SEARCH_BUTTON = nameAttribute("input", "submit");

    public static void search(String search) throws MissingDriverException {
        enterField(SEARCH, search);
        click(SEARCH_BUTTON);
    }

    public static boolean isTrue() {
        return Boolean.valueOf(NO_RESULTS_SEARCH);
    }

    public static void licence() throws MissingDriverException {
        click(LICENCE);
    }

    public static void applications() throws MissingDriverException {
        click(APPLICATIONS);

    }

    public static void cases() throws MissingDriverException {
        click(CASE);
    }

    public static void psvdisc() throws MissingDriverException {
        click(PSVDISC);
    }

    public static void vehicle() throws MissingDriverException {
        click(VEHICLE);
    }

    public static void address() throws MissingDriverException {
        click(ADDRESS);
    }

    public static void busRegistrations() throws MissingDriverException {
        click(BUSREGISTRATIONS);
    }

    public static void people() throws MissingDriverException {
        click(PEOPLE);
    }

    public static void users() throws MissingDriverException {
        click(USERS);
    }

    public static void publication() throws MissingDriverException {
        click(PUBLICATION);
    }

    public static void irfo() throws MissingDriverException {
        click(IRFO);
    }


}
