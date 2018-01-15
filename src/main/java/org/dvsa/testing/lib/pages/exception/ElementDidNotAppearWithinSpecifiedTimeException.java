package org.dvsa.testing.lib.pages.exception;

import activesupport.system.out.Output;

public class ElementDidNotAppearWithinSpecifiedTimeException extends Exception {

    public ElementDidNotAppearWithinSpecifiedTimeException() {
        super(Output.printColoredLog("[ERROR] Element did not appear in the specified time"));
    }

    public ElementDidNotAppearWithinSpecifiedTimeException(String message) {
        super(message);
    }

    public ElementDidNotAppearWithinSpecifiedTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementDidNotAppearWithinSpecifiedTimeException(Throwable cause) {
        super(cause);
    }
}
