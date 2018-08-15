package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.external.permit.enums.Sector;

public class SectorPage extends BasePermitPage {

    private static String SPECIALISES_TEMPLATE = "fieldset[data-group='Fields'] > label:nth-of-type(%d)";
    private static String SECTOR_TEMPLATE = ".sector-list label:nth-of-type(%d) input[type='radio']";

    public static void specialist(boolean specialist){
        int index = specialist ? 1 : 2;
        scrollAndClick(String.format(SPECIALISES_TEMPLATE, index));
    }

    public static void sector(Sector sector){
        scrollAndClick(String.format(SECTOR_TEMPLATE, sector.ordinal() + 1));
    }

}
