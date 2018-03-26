package org.dvsa.testing.lib.pages.internal;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import activesupport.string.Str;
import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class Application extends BasePage {

    public static String INTERIM_LINK = "//*/a[contains(text(), 'Interim details')]";

        private static void interimLink() throws UninitialisedDriverException {
            click(INTERIM_LINK);
        }
    }
