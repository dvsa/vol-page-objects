package org.dvsa.testing.lib.pages.external.permit.enums;

import org.jetbrains.annotations.NotNull;

public enum PermitSection {

    LicenceNumber("Licence number"),
    EuroEmissionsStandards("Euro emission standards"),
    Cabotage("Cabotage"),
    GoodsToLimitedCountries("Goods to limited countries"),
    NumberOfPermitsRequired("Number of permits required"),
    AnnualTripsAbroad("Annual trips abroad"),
    PercentageOfInternationalJourneys("Percentage of international journeys"),
    GoodsYouCarryAbroad("Goods you carry abroad"),
    CheckYourAnswers("Check your answers"),
    Declaration("Declaration");

    private String section;

    PermitSection(String section){
        this.section = section;
    }

    public static PermitSection getEnum(@NotNull String section) {
        PermitSection sectionEnum;

        switch (section.toLowerCase().trim()){
            case "licence number":
                sectionEnum = LicenceNumber;
                break;
            case "euro emission standards":
                sectionEnum = EuroEmissionsStandards;
                break;
            case "cabotage":
                sectionEnum = Cabotage;
                break;
            case "goods to limited countries":
                sectionEnum = GoodsToLimitedCountries;
                break;
            case "number of permits required":
                sectionEnum = NumberOfPermitsRequired;
                break;
            case "annual trips abroad":
                sectionEnum = AnnualTripsAbroad;
                break;
            case "percentage of international journeys":
                sectionEnum = PercentageOfInternationalJourneys;
                break;
            case "goods you carry abroad":
                sectionEnum = GoodsYouCarryAbroad;
                break;
            case "check your answers":
                sectionEnum = CheckYourAnswers;
                break;
            case "declaration":
                sectionEnum = Declaration;
                break;
            default:
                throw new IllegalArgumentException("Unable to convert to enum using section of: ".concat(section));
        }

        return  sectionEnum;
    }

    @Override
    public String toString() {
        return section;
    }

}
