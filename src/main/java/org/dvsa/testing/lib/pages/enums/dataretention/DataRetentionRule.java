package org.dvsa.testing.lib.pages.enums.dataretention;

import activesupport.system.out.Output;

/**
 * This enum represents the options available under the rule admin tab on data retention.
 */
public enum DataRetentionRule {
    IRFO_OPERATOR_EXPIRED ("IRFO Operator Expired"),
    IRFO_GV_PERMIT_EXPIRED("IRFO GV Permit Expired"),
    IRFO_PSV_AUTHORISATION_EXPIRED("IRFO PSV Authorisation Expired"),
    IRFO_PSV_AUTH_WITHDRAWN_PENDING_REFUSED_ETC_EXPIRED("IRFO PSV Auth Withdrawn,Pending,Refused Etc Expired"),
    IRFO_GV_PERMIT_WITHDRAWN_PENDING_OR_REFUSED_EXPIRED("IRFO GV Permit Withdrawn Pending Or Refused Expired"),
    LICENCE_NOT_YET_SUBMITTED("Licence Not Yet Submitted"),
    APPLICATIONS_REFUSED("Applications Refused"),
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

        switch (value.toLowerCase()) {
            case "irfo operator expired":
                dataRetentionRule = DataRetentionRule.IRFO_OPERATOR_EXPIRED;
                break;
            case "irfo gv permit expired":
                dataRetentionRule = DataRetentionRule.IRFO_GV_PERMIT_EXPIRED;
                break;
            case "irfo psv authorisation expired":
                dataRetentionRule = DataRetentionRule.IRFO_PSV_AUTHORISATION_EXPIRED;
                break;
            case "irfo psv auth withdrawn,pending,refused etc expired":
                dataRetentionRule = DataRetentionRule.IRFO_PSV_AUTH_WITHDRAWN_PENDING_REFUSED_ETC_EXPIRED;
                break;
            case "irfo gv permit withdrawn pending or refused expired":
                dataRetentionRule = DataRetentionRule.IRFO_GV_PERMIT_WITHDRAWN_PENDING_OR_REFUSED_EXPIRED;
                break;
            case "licence not yet submitted":
                dataRetentionRule = DataRetentionRule.LICENCE_NOT_YET_SUBMITTED;
                break;
            case "applications refused":
                dataRetentionRule = DataRetentionRule.APPLICATIONS_REFUSED;
                break;
            case "transport Managers with Public inquirys":
                dataRetentionRule = DataRetentionRule.TRANSPORT_MANAGERS_WITH_PUBLIC_INQUIRYS;
                break;
            case "licence with pi, no impounding and not unlicenced":
                dataRetentionRule = DataRetentionRule.LICENCE_WITH_PI_NO_IMPOUNDING_AND_NOT_UNLICENCED;
                break;
            case "licence with impounding and not unlicenced":
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
