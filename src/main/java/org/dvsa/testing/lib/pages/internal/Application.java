package org.dvsa.testing.lib.pages.internal;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import activesupport.string.Str;
import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class Application extends BasePage {

    public static String INTERIM_LINK = "//*/a[contains(text(), 'Interim details')]";

    public static String SAVE_BUTTON = nameAttribute("input","\"form-actions[save]\"");

        public static void interimLink() throws UninitialisedDriverException {
            click(INTERIM_LINK);
        }

        public static void save() {
            click(SAVE_BUTTON);
        }
    }
