package org.dvsa.testing.lib.pages.external.permit.enums;

import activesupport.number.Int;

public enum Sector {

    FOOD,
    MAIL,
    EQUIPMENT,
    MINING_AND_QUARRYING_PRODUCTS,
    CHEMICALS,
    NON_METAL_MINERALS,
    WOOD_PRODUCTS,
    FURNITURE,
    MUNICIPAL_WASTES_AND_OTHER_WASTES,
    REFINED_PETROLEUM,
    TEXTILES_AND_LEATHER_PRODUCTS,
    COAL_AND_CRUDE_PETROLEUM,
    OTHER;

    public static Sector random(){
        return Sector.values()[Int.random(1, Sector.values().length)];
    }

}
