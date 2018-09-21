package org.dvsa.testing.lib.pages.external.permit.enums;

import org.jetbrains.annotations.NotNull;

public enum FeeSection {

    ApplicationReference("Application reference"),
    ApplicationDate("Application date"),
    PermitType("Permit type"),
    PermitStartAndEndDate("Permit start and end date"),
    PermitsRequired("Permits required"),
    TotalApplicationFeeToBePaid("Total application fee to be paid");

    private String section;

    FeeSection(@NotNull String section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return section;
    }

}
