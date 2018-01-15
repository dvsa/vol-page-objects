package org.dvsa.testing.lib.pages.exception;

import activesupport.system.out.Output;

public class InsufficientExistingDataException extends Exception {
    public InsufficientExistingDataException() {
        super(Output.printColoredLog("[ERROR] Unable to find existing data in the web application in order to continue the current test."));
    }

    public InsufficientExistingDataException(String message) {
        super(message);
    }

    public InsufficientExistingDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientExistingDataException(Throwable cause) {
        super(cause);
    }
}
