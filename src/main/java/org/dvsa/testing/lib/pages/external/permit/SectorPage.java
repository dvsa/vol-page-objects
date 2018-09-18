package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.permit.enums.Sector;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SectorPage extends BasePermitPage {

    private static String SECTOR_TEMPLATE = "#SpecialistHaulage label:nth-of-type(%d) input[type=radio]";

    private static String SECTOR_TITLE = "label b";

    final public static String RESOURCE = "ecmt-sectors/";

    public static void sector(Sector sector){
        scrollAndClick(String.format(SECTOR_TEMPLATE, sector.ordinal() + 1));
    }

    public static List<String> getSectorsOnPage() {
        List<WebElement> sectors = findAll(SECTOR_TITLE, SelectorType.CSS);
        return sectors.stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
