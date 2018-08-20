package org.dvsa.testing.lib.enums;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public enum PermitStatus {

    NOT_YET_SUBMITTED("Not Yet Submitted"),
    CANCELLED("Cancelled"),
    UNDER_CONSIDERATION("Under Consideration");

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
