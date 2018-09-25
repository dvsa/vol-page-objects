package org.dvsa.testing.lib.pages.external.permit.enums;

import activesupport.number.Int;

public enum Sector {

    CHEMICALS("Chemical products, man-made fibres, rubber and plastic products, nuclear fuel"),
    FOOD_PRODUCTS("Beverages and tobacco, products of agriculture, hunting and forests, fish and other fishing products"),
    FURNITURE("Other manufactured goods not elsewhere categorised, goods moved in course of household and office removals"),
    METAL("Metal ores and other mining and quarrying products, peat, uranium and thorium, basic metals, fabricated metal products, except machinery and equipment"),
    MAIL_AND_PARCELS("Mail, parcels, grouped goods"),
    RAW_MATERIALS("Municipal wastes and other wastes"),
    REFINED_FUELS("Coke and refined petroleum products"),
    TRANSPORT_AND_MACHINERY("Equipment, machinery and equipment not elsewhere categorised, equipment and material utilised in the transport of goods"),
    TEXTILES("Textile products, leather and leather products"),
    UNREFINED_FUELS("Lignite, crude petroleum and natural gas"),
    WOOD("Products of wood and cork (except furniture), articles of straw and plaiting materials, pulp, paper and paper products, printed matter and recorded media"),
    OTHER_NON_METALLIC_MINERAL_PRODUCT("Other non-metallic mineral products"),
    NONE_OR_MORE_THAN_ONE_OF_THESE_SECTORS("None/More than one of these sectors");

    private String description;

    Sector(String description) {
        this.description = description;
    }

    public static Sector random(){
        return Sector.values()[Int.random(0, Sector.values().length - 1)];
    }

    @Override
    public String toString() {
        return description;
    }

}
