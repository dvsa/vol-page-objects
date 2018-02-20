package org.dvsa.testing.lib.pages.enums.dataretention;

import org.jetbrains.annotations.NotNull;

public enum SystemParameter {
    DATA_RETENTION_DELETE("DISABLE_DATA_RETENTION_DELETE"),
    DATA_RETENTION_RECORDS("DISABLE_DATA_RETENTION_RECORDS"),
    DR_DELETE_LIMIT("DR_DELETE_LIMIT"),
    DR_DOCUMENT_DELETE("DISABLE_DR_DOCUMENT_DELETE");

    private String name;
    private String enableValue;
    private String disableValue;

    SystemParameter(@NotNull String name) {
        this.name = name;
    }

    static {
        DATA_RETENTION_DELETE.enableValue = "0";
        DATA_RETENTION_RECORDS.enableValue = "0";
        DR_DOCUMENT_DELETE.enableValue = "0";

        DATA_RETENTION_DELETE.disableValue = "1";
        DATA_RETENTION_RECORDS.disableValue = "1";
        DR_DOCUMENT_DELETE.disableValue = "1";
    }

    public String getEnableValue() {
        return this.enableValue;
    }

    public String getDisableValue() {
        return this.disableValue;
    }

    public String getName() {
        return this.name;
    }

}
