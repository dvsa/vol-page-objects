package org.dvsa.testing.lib.pages.exception;

import org.openqa.selenium.WebDriverException;

public class FoundElementException extends WebDriverException {
    public FoundElementException() {
    }

    public FoundElementException(String message) {
        super(message);
    }

    public FoundElementException(Throwable cause) {
        super(cause);
    }

    public FoundElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
