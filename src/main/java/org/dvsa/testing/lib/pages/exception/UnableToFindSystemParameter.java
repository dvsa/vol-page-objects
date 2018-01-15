package org.dvsa.testing.lib.pages.exception;

import activesupport.system.out.Output;

public class UnableToFindSystemParameter extends Exception {
    public UnableToFindSystemParameter() {
        super(Output.printColoredLog("[ERROR] Unable to find the specified system property through all paginated pages"));
    }

    public UnableToFindSystemParameter(String message) {
        super(message);
    }

    public UnableToFindSystemParameter(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableToFindSystemParameter(Throwable cause) {
        super(cause);
    }
}
