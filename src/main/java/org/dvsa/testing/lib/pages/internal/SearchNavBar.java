package org.dvsa.testing.lib.pages.internal;

import activesupport.IllegalBrowserException;
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

    public static void search(String search) throws IllegalBrowserException {
        enterField(SEARCH, search);
        click(SEARCH_BUTTON);
    }

    public static boolean isTrue() {
        return Boolean.valueOf(NO_RESULTS_SEARCH);
    }

    public static void licence() throws IllegalBrowserException {
        click(LICENCE);
    }

    public static void applications() throws IllegalBrowserException {
        click(APPLICATIONS);

    }

    public static void cases() throws IllegalBrowserException {
        click(CASE);
    }

    public static void psvdisc() throws IllegalBrowserException {
        click(PSVDISC);
    }

    public static void vehicle() throws IllegalBrowserException {
        click(VEHICLE);
    }

    public static void address() throws IllegalBrowserException {
        click(ADDRESS);
    }

    public static void busRegistrations() throws IllegalBrowserException {
        click(BUSREGISTRATIONS);
    }

    public static void people() throws IllegalBrowserException {
        click(PEOPLE);
    }

    public static void users() throws IllegalBrowserException {
        click(USERS);
    }

    public static void publication() throws IllegalBrowserException {
        click(PUBLICATION);
    }

    public static void irfo() throws IllegalBrowserException {
        click(IRFO);
    }


}
