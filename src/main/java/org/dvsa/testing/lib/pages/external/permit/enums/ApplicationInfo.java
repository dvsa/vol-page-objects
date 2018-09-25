package org.dvsa.testing.lib.pages.external.permit.enums;

public enum ApplicationInfo {

    Licence("Licence selected"),
    Euro6("I confirm that my ECMT permit(s) will only be used by vehicle(s) that are environmentally compliant to Euro 6 emissions standards."),
    Cabotage("I confirm that I will not undertake a cabotage journey(s) with an ECMT permit."),
    RestrictedCountries("In the next 12 months are you transporting goods to Austria, Greece, Hungary, Italy or Russia?"),
    NumberOfPermits("How many permits do you require for this licence?"),
    NumberOfTrips("How many international trips did you make in the last 12 months using this licence?"),
    ProportionOfInternationalBusiness("In the last 12 months what portion of your journeys were international for this licence?"),
    Sector("Select one sector you mainly transport goods in");

    private String description;

    ApplicationInfo(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

}
