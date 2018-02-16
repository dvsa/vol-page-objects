package org.dvsa.testing.lib.pages.external.licence;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.Util;

public class LicenceHistoryPage extends BasePage {
    // Selectors
    private static String TRAFFIC_AREAS = "form > fieldset:nth-of-type(2)";
    private static String QUESTION_1 = TRAFFIC_AREAS + " > fieldset label:nth-of-type(%d) input";
    private static String QUESTION_2 = TRAFFIC_AREAS + " > fieldset:nth-of-type(3) label:nth-of-type(%d) input";
    private static String QUESTION_3 = TRAFFIC_AREAS + " > fieldset:nth-of-type(5) label:nth-of-type(%d) input";

    private static String EU = "form > fieldset:nth-of-type(3)";
    private static String QUESTION_4 = EU + " > fieldset label:nth-of-type(%d) input";
    private static String QUESTION_5 = EU + " > fieldset:nth-of-type(3) label:nth-of-type(%d) input";

    private static String PUBLIC_INQUIRIES = "form > fieldset:nth-of-type(4)";
    private static String QUESTION_6 = PUBLIC_INQUIRIES + " label:nth-of-type(%d) input";

    private static String ASSETS_AND_SHARES = "form > fieldset:nth-of-type(5)";
    private static String QUESTION_7 = ASSETS_AND_SHARES + "label:nth-of-type(%d) input";

    private static String SAVE_AND_CONTINUE_BUTTON = "button[name='form-actions[saveAndContinue]']";
    private static String SAVE_AND_RETURN_TO_OVERVIEW_BUTTON = "button[name='form-actions[save]']";

    public static void trafficAreasQ1(boolean answer) throws UninitialisedDriverException {
        int position = Util.radioPosition(answer);
        click(String.format(QUESTION_1, position));
    }

    public static void trafficAreasQ2(boolean answer) throws UninitialisedDriverException {
        int position = Util.radioPosition(answer);
        click(String.format(QUESTION_2, position));
    }

    public static void trafficAreasQ3(boolean answer) throws UninitialisedDriverException {
        int position = Util.radioPosition(answer);
        click(String.format(QUESTION_3, position));
    }

    public static void EUQ1(boolean answer) throws UninitialisedDriverException {
        int position = Util.radioPosition(answer);
        click(String.format(QUESTION_4, position));
    }

    public static void EUQ2(boolean answer) throws UninitialisedDriverException {
        int position = Util.radioPosition(answer);
        click(String.format(QUESTION_5, position));
    }

    public static void publicInquiriesQ1(boolean answer) throws UninitialisedDriverException {
        int position = Util.radioPosition(answer);
        click(String.format(QUESTION_6, position));
    }

    public static void assetsAndSharesQ1(boolean answer) throws UninitialisedDriverException {
        int position = Util.radioPosition(answer);
        click(String.format(QUESTION_7, position));
    }

    public static void saveAndContinue() throws UninitialisedDriverException {
        click(SAVE_AND_CONTINUE_BUTTON);
    }

    public static void saveAndReturnToOverview() throws UninitialisedDriverException {
        click(SAVE_AND_RETURN_TO_OVERVIEW_BUTTON);
    }

}
