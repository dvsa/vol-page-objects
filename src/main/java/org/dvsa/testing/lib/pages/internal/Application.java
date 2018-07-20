package org.dvsa.testing.lib.pages.internal;

import activesupport.MissingDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import activesupport.string.Str;
import activesupport.MissingDriverException;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class Application extends BasePage {

    public static String INTERIM_LINK = "//*/a[contains(text(),'add interim')]";

    public static String SAVE_BUTTON = nameAttribute("input","\"form-actions[save]\"");

        public static void interimLink() throws MissingDriverException {
            click(INTERIM_LINK, SelectorType.XPATH);
        }

        public static void save() {
            click(SAVE_BUTTON);
        }
    }
