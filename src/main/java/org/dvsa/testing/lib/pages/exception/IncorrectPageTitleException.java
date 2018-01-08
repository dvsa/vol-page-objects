package org.dvsa.testing.lib.pages.exception;

import activesupport.system.out.Output;

public class IncorrectPageTitleException extends Exception {

    public IncorrectPageTitleException() {
        super(Output.printColoredLog("[ERROR] Found unexpected page title"));
    }

    public IncorrectPageTitleException(String message) {
        super(message);
    }

    public IncorrectPageTitleException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPageTitleException(Throwable cause) {
        super(cause);
    }

}
