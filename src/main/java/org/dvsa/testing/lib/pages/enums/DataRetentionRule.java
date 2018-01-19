package org.dvsa.testing.lib.pages.enums;

/**
 * This enum represents the options available under the rule admin tab on data retention.
 */
public enum DataRetentionRule {
    TRANSPORT_MANAGERS_WITH_PUBLIC_INQUIRYS ("Transport Managers With Public Inquirys"),
    LICENCE_WITH_PI_NO_IMPOUNDING_AND_NOT_UNLICENCED ("Licence With Pi, No Impounding And Not Unlicenced"),
    LICENCE_WITH_IMPOUNDING_AND_NOT_UNLICENCED ("Licence With Impounding And Not Unlicenced");

    private final String name;

    DataRetentionRule(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
