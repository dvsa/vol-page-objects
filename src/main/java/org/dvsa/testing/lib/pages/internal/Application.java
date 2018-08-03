package org.dvsa.testing.lib.pages.internal;

import activesupport.IllegalBrowserException;
import org.dvsa.testing.lib.pages.BasePage;
import activesupport.string.Str;
import activesupport.IllegalBrowserException;
import org.dvsa.testing.lib.pages.enums.SelectorType;

public class Application extends BasePage {

    public static String INTERIM_LINK = "//*/a[contains(text(),'add interim')]";

    public static String SAVE_BUTTON = nameAttribute("input","\"form-actions[save]\"");

        public static void interimLink() throws IllegalBrowserException {
            click(INTERIM_LINK, SelectorType.XPATH);
        }

        public static void save() throws IllegalBrowserException{
            click(SAVE_BUTTON);
        }
    }
