package org.dvsa.testing.lib.pages.enums;

import activesupport.system.out.Output;
import org.jetbrains.annotations.NotNull;

public enum BusinessType {
    LIMITED_COMPANY("limited company"),
    SOLE_TRADER("sole trader"),
    PARTNERSHIP("partnership"),
    LIMITED_LIABILITY_COMPANY("limited liability company"),
    OTHER("other");

    private String name;

    BusinessType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BusinessType getEnum(@NotNull String businessType) {
        BusinessType businessTypeEnum;

        switch(businessType){
            case "limited company":
                businessTypeEnum = LIMITED_COMPANY;
                break;
            case "sole trader":
                businessTypeEnum = SOLE_TRADER;
                break;
            case "partnership":
                businessTypeEnum = PARTNERSHIP;
                break;
            case "limited liability company":
                businessTypeEnum = LIMITED_LIABILITY_COMPANY;
                break;
            case "other":
                businessTypeEnum = OTHER;
                break;
            default:
                throw new IllegalArgumentException(Output.printColoredLog("[ERROR] Unable to convert " + businessType + " into a BusinessType enum"));
        }

        return businessTypeEnum;
    }
}
