package org.dvsa.testing.lib.enums;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public enum PermitType {

    ECMT("ECMT");

    private String type;

    PermitType(String type) {
        this.type = type;
    }

    public static PermitType getEnum(@NotNull String name) {
        PermitType permitType;

        switch (StringUtils.deleteWhitespace(name).toLowerCase()) {
            case "ecmt":
                permitType = ECMT;
                break;
            default:
                throw new IllegalArgumentException("Unsupported value: ".concat(name));
        }

        return permitType;
    }

    @Override
    public String toString() {
        return type;
    }

}
