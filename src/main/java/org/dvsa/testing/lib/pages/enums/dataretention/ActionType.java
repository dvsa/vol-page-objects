package org.dvsa.testing.lib.pages.enums.dataretention;

import activesupport.system.out.Output;
import org.jetbrains.annotations.NotNull;

public enum ActionType {

    REVIEW,
    AUTOMATE;

    public static ActionType getEnum(@NotNull String actionType) {
        ActionType action;

        switch (actionType.toLowerCase()) {
            case "automate":
                action = ActionType.AUTOMATE;
                break;
            case "review":
                action = ActionType.REVIEW;
                break;
            default:
                throw new IllegalArgumentException(Output.printColoredLog(String.format(
                        "[ERROR] Unsupported NAME: %S passed as an argument.",
                        actionType
                )));
        }

        return action;
    }

}
