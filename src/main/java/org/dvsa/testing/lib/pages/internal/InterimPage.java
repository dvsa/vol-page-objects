package org.dvsa.testing.lib.pages.internal;

import activesupport.IllegalBrowserException;
import org.dvsa.testing.lib.pages.BasePage;

public class InterimPage extends BasePage{

    //Selectors
    private static String ADD_INTERIM_RADIO_YES = "fieldset.inline label:nth-of-type(2)";
    private static String INTERIM_REASON_FIELD = nameAttribute("textarea","data[interimReason]");
    private static String START_DATE_DAY_FIELD = nameAttribute("input", "data[interimStart][day]");
    private static String START_DATE_MONTH_FIELD = nameAttribute("input", "data[interimStart][month]");
    private static String START_DATE_YEAR_FIELD = nameAttribute("input", "data[interimStart][year]");
    private static String END_DATE_DAY_FIELD = nameAttribute("input", "data[interimEnd][day]");
    private static String END_DATE_MONTH_FIELD = nameAttribute("input", "data[interimEnd][month]");
    private static String END_DATE_YEAR_FIELD = nameAttribute("input", "data[interimEnd][year]");
    private static String VEHICLE_FIELD = nameAttribute("input", "data[interimAuthVehicles]");
    private static String TRAILERS_FIELD = nameAttribute("input", "data[interimAuthTrailers]");
    private static String OPERATING_CENTRE_FIELD = nameAttribute("input", "operatingCentres[id][]");
    private static String VEHICLE_SELECT_FIELD = nameAttribute("input", "vehicles[id][]");
    private static String SAVE = nameAttribute("button", "form-actions[save]");
    private static String GRANT = nameAttribute("button", "form-actions[grant]");

    //Behaviour
    public static void addInterim() throws IllegalBrowserException {
        click(ADD_INTERIM_RADIO_YES);
    }

    public static void  enterInterimDetail(String interimDetails) throws IllegalBrowserException {
          enterField(INTERIM_REASON_FIELD, interimDetails);
    }

    public static void startDate(int day, int month, int year) throws IllegalBrowserException {
        enterField(START_DATE_DAY_FIELD, String.valueOf(day));
        enterField(START_DATE_MONTH_FIELD, String.valueOf(month));
        enterField(START_DATE_YEAR_FIELD, String.valueOf(year));
    }

    public static void endDate(int day, int month, int year) throws IllegalBrowserException {
        enterField(END_DATE_DAY_FIELD, String.valueOf(day));
        enterField(END_DATE_MONTH_FIELD, String.valueOf(month));
        enterField(END_DATE_YEAR_FIELD, String.valueOf(year));
    }

    public static void vehicleAuthority(int vehicles) throws IllegalBrowserException {
        enterField(VEHICLE_FIELD, String.valueOf(vehicles));
    }

    public static void trailerAuthority(int trailer) throws IllegalBrowserException {
        enterField(TRAILERS_FIELD, String.valueOf(trailer));
    }

    public static void operatingCentre() throws IllegalBrowserException {
        click(OPERATING_CENTRE_FIELD);
    }


    public static void vehicleSelect() throws IllegalBrowserException {
        click(VEHICLE_SELECT_FIELD);
    }

    public static void save() throws IllegalBrowserException {
        click(SAVE);
    }

    public static void grant() throws IllegalBrowserException {
        click(GRANT);
    }
}
