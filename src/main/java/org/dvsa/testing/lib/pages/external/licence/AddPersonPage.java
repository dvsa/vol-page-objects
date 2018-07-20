package org.dvsa.testing.lib.pages.external.licence;

import activesupport.MissingDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.external.licence.enums.Calendar;
import org.dvsa.testing.lib.pages.external.licence.enums.Title;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AddPersonPage extends BasePage {
    // Selectors
    private static String TITLE_LIST_TEMPLATE = "select[name='data[title]']";
    private static String FIRST_NAME_FIELD = "input[name='data[forename]']";
    private static String LAST_NAME_FIELD = "input[name='data[familyName]']";
    private static String OTHER_NAME = "input[name='data[otherName]']";
    private static String DATE_TEMPLATE = ".date > div:nth-of-type(%d) input";

    private static String SAVE_BUTTON = "button[name='form-actions[submit]']";
    private static String CANCEL_BUTTON = "button[name='form-actions[cancel]']";

    // Attributes
    private static String PAGE_TITLE_TEXT = "Add person";
    private static String RESOURCE_PATH = "application/\\d{7}/people/add/";
    // Behaviour
    public static void title(@NotNull Title title) throws MissingDriverException {
        list(TITLE_LIST_TEMPLATE, title.ordinal() + 1);
    }

    public static void firstName(@NotNull String name) throws MissingDriverException {
        enterField(FIRST_NAME_FIELD, name);
    }

    public static void lastName(@NotNull String name) throws MissingDriverException {
        enterField(LAST_NAME_FIELD, name);
    }

    public static void otherName(@NotNull String name) throws MissingDriverException {
        enterField(OTHER_NAME, name);
    }

    public static void dateOfBirth(@NotNull HashMap<Calendar, String> calendar) throws MissingDriverException {
        for (Map.Entry<Calendar, String> date : calendar.entrySet()) {
            enterField(dateSelector(date.getKey()), date.getValue());
        }
    }

    private static String dateSelector(@NotNull Calendar calendar) {
        return String.format(DATE_TEMPLATE, calendar.ordinal() + 1);
    }

    public static void saveButton() throws MissingDriverException {
        click(SAVE_BUTTON);
    }

    public static void calcelButton() throws MissingDriverException {
        click(CANCEL_BUTTON);
    }
}
