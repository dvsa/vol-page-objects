package org.dvsa.testing.lib.enums;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public enum PermitStatus {

    NOT_YET_SUBMITTED("Not Yet Submitted"),
    CANCELLED("Cancelled"),
    UNDER_CONSIDERATION("Under Consideration"),
    AWAITING_FEE("Awaiting Fee"),
    NOT_STARTED_YET("Not started yet"),
    COMPLETED("Completed"),
    CANT_START_YET("Can't start yet");

    private String status;

    PermitStatus(String status) {
        this.status = status;
    }

    public static PermitStatus getEnum(@NotNull String name) {
        PermitStatus permitStatus;

        switch(StringUtils.trim(name).toLowerCase()) {
            case "not yet submitted":
                permitStatus = NOT_YET_SUBMITTED;
                break;
            case "cancelled":
                permitStatus = CANCELLED;
                break;
            case "under consideration":
                permitStatus = UNDER_CONSIDERATION;
                break;
            case "awaiting fee":
                permitStatus = AWAITING_FEE;
                break;
            case "not started yet":
                permitStatus = NOT_STARTED_YET;
                break;
            case "completed":
                permitStatus = COMPLETED;
                break;
            case "can't start yet":
                permitStatus = CANT_START_YET;
                break;
            default:
                throw new IllegalArgumentException("Unable to convert to enum, name: ".concat(name));
        }

        return permitStatus;
    }

    @Override
    public String toString() {
        return status;
    }

}
