package org.dvsa.testing.lib.pages.enums.dataretention;

import activesupport.system.out.Output;

/**
 * This enum represents the options available under the rule admin tab on data retention.
 */
public enum DataRetentionRule {
    IRFO_OPERATOR_EXPIRED ("IRFO Operator Expired"),
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

    public static DataRetentionRule getEnum(String value) {
        DataRetentionRule dataRetentionRule;

        switch (value) {
            case "IRFO Operator Expired":
                dataRetentionRule = DataRetentionRule.IRFO_OPERATOR_EXPIRED;
                break;
            case "Transport Managers With Public Inquirys":
                dataRetentionRule = DataRetentionRule.TRANSPORT_MANAGERS_WITH_PUBLIC_INQUIRYS;
                break;
            case "Licence With Pi, No Impounding And Not Unlicenced":
                dataRetentionRule = DataRetentionRule.LICENCE_WITH_PI_NO_IMPOUNDING_AND_NOT_UNLICENCED;
                break;
            case "Licence With Impounding And Not Unlicenced":
                dataRetentionRule = DataRetentionRule.LICENCE_WITH_IMPOUNDING_AND_NOT_UNLICENCED;
                break;
            default:
                throw new IllegalArgumentException(Output.printColoredLog(String.format("[ERROR] '%s' is not a supported argument", value)));
        }

        return dataRetentionRule;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
