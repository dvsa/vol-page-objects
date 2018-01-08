package org.dvsa.testing.lib.pages.exception;

import activesupport.system.out.Output;

public class IncorrectURLException extends Exception {

    public IncorrectURLException() {
        super(Output.printColoredLog("[ERROR] Found unexpected URL"));
    }

    public IncorrectURLException(String message) {
        super(message);
    }

    public IncorrectURLException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectURLException(Throwable cause) {
        super(cause);
    }

}
