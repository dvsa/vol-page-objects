package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.external.permit.enums.Sector;

public class SectorPage extends BasePermitPage {

    private static String SECTOR_TEMPLATE = "#SpecialistHaulage label:nth-of-type(%d) input[type=radio]";

    final public static String RESOURCE = "ecmt-sectors/";

    public static void sector(Sector sector){
        scrollAndClick(String.format(SECTOR_TEMPLATE, sector.ordinal() + 1));
    }

}
