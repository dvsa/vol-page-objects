package org.dvsa.testing.lib.pages.exception;

import activesupport.system.out.Output;

public class UnableToFindDataRetentionRule extends Exception {
    public UnableToFindDataRetentionRule() {
        super(Output.printColoredLog("[ERROR] Unable to find data retention rule."));
    }

    public UnableToFindDataRetentionRule(String message) {
        super(Output.printColoredLog("[ERROR] " + message));
    }

    public UnableToFindDataRetentionRule(String message, Throwable cause) {
        super(Output.printColoredLog("[ERROR] " + message), cause);
    }

    public UnableToFindDataRetentionRule(Throwable cause) {
        super(cause);
    }
}
