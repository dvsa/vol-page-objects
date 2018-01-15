package org.dvsa.testing.lib.pages.exception;

import activesupport.system.out.Output;

public class ElementDidNotDisappearWithinSpecifiedTimeException extends Exception {

    public ElementDidNotDisappearWithinSpecifiedTimeException() {
        super(Output.printColoredLog("[ERROR] Element did not disappear in the specified time"));
    }

    public ElementDidNotDisappearWithinSpecifiedTimeException(String message) {
        super(message);
    }

    public ElementDidNotDisappearWithinSpecifiedTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementDidNotDisappearWithinSpecifiedTimeException(Throwable cause) {
        super(cause);
    }
}
