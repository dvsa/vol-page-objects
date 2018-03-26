package org.dvsa.testing.lib.pages.internal;

import activesupport.string.Str;
import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class InterimPage extends BasePage{

    //Selectors
    private static String ADD_INTERIM_RADIO_YES = nameAttribute("radio","interimRequested");
    private static String INTERIM_REASON_TEXTBOX = "data[interimReason]";
    private static String SDATE_DAY_TEXTBOX =nameAttribute("input", "data[interimStart][day]");
    private static String SDATE_MONTH_TEXTBOX=nameAttribute("input", "data[interimStart][month]");
    private static String SDATE_YEAR_TEXTBOX=nameAttribute("input", "data[interimStart][year]");
    private static String EDATE_DAY_TEXTBOX =nameAttribute("input", "data[interimEnd][day]");
    private static String EDATE_MONTH_TEXTBOX=nameAttribute("input", "data[interimEnd][month]");
    private static String EDATE_YEAR_TEXTBOX=nameAttribute("input", "data[interimEnd][year]");
    private static String VECHICLE_TEXTBOX = nameAttribute("input", "data[interimAuthVehicles]");
    private static String TRAILERS_TEXTBOX = nameAttribute("input", "data[interimAuthTrailers]");
    private static String OPERATING_CENTRE = nameAttribute("input", "operatingCentres[id][]");
    private static String VEHICLE_SELECT = nameAttribute("inpus", "vehicles[id][]");
    private static String SAVE = nameAttribute("input", "form-actions[save]");

    //Behaviour
    public static void selectInterimRadioYes() throws UninitialisedDriverException {
        click(ADD_INTERIM_RADIO_YES);
    }

    public static void  enterInterimDetails() throws UninitialisedDriverException {
          enterField(INTERIM_REASON_TEXTBOX, Str.randomWord(150));
    }


    public static void startDate(int day, int month, int year) throws UninitialisedDriverException {
        enterField(SDATE_DAY_TEXTBOX, String.valueOf(day));
        enterField(SDATE_MONTH_TEXTBOX,String.valueOf(month));
        enterField(SDATE_YEAR_TEXTBOX,String.valueOf(year));
    }

    public static void endDate(int day, int month, int year) throws UninitialisedDriverException {
        enterField(EDATE_DAY_TEXTBOX, String.valueOf(day));
        enterField(EDATE_MONTH_TEXTBOX, String.valueOf(month));
        enterField(EDATE_YEAR_TEXTBOX, String.valueOf(year));
    }

    public static void vehicleAuthority(int vehicles) throws UninitialisedDriverException {
                enterField(VECHICLE_TEXTBOX, String.valueOf(vehicles));
    }

    public static void trailerAuthority(int trailer) throws UninitialisedDriverException {
        enterField(TRAILERS_TEXTBOX, String.valueOf(trailer));
    }

    public static void operatingCentre() throws UninitialisedDriverException {
        click(OPERATING_CENTRE);
    }

    public static void vehicleSelect() throws UninitialisedDriverException {
         click(VEHICLE_SELECT);
    }

    public static void save() throws UninitialisedDriverException{
        click(SAVE);
    }
}
