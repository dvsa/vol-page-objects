package org.dvsa.testing.lib.pages.enums.dataretention;

public enum SystemParameter {
    DISABLE_DATA_RETENTION_DELETE,
    DISABLE_DATA_RETENTION_RECORDS,
    DR_DELETE_LIMIT,
    DISABLE_DR_DOCUMENT_DELETE;

    private String enableValue;
    private String disableValue;

    static {
        DISABLE_DATA_RETENTION_DELETE.enableValue = "0";
        DISABLE_DATA_RETENTION_RECORDS.enableValue = "0";
        DISABLE_DR_DOCUMENT_DELETE.enableValue = "0";

        DISABLE_DATA_RETENTION_DELETE.enableValue = "1";
        DISABLE_DATA_RETENTION_RECORDS.enableValue = "1";
        DISABLE_DR_DOCUMENT_DELETE.enableValue = "1";
    }

    public String getEnableValue() {
        return this.enableValue;
    }

    public String getDisableValue() {
        return this.disableValue;
    }

}
