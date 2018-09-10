package org.dvsa.testing.lib.pages.external.permit.enums;

public enum PermitSection {

    LicenceNumber("Licence number"),
    EuroEmmistionsStandards("Euro emission standards"),
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

    @Override
    public String toString() {
        return section;
    }

}
