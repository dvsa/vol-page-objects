package org.dvsa.testing.lib.pages.external.permit.enums;

import activesupport.number.Int;
import org.jetbrains.annotations.NotNull;

public enum Sector {

    FOOD_PRODUCTS("Beverages and tobacco, products of agriculture, hunting and forests, fish and other fishing products"),
    CHEMICALS("Chemical products, man-made fibres, rubber and plastic products, nuclear fuel"),
    COKE_AND_REFINED_PETROLEUM_PRODUCTS("Coke and refined petroleum products"),
    TRANSPORT_EQUIPMENT("Equipment, machinery and equipment not elsewhere categorised, equipment and material utilised in the transport of goods"),
    COAL("Lignite, crude petroleum and natural gas"),
    MAIL_AND_PARCELS("Mail, parcels, grouped goods"),
    METAL("Metal ores and other mining and quarrying products, peat, uranium and thorium, basic metals, fabricated metal products, except machinery and equipment"),
    RAW_MATERIALS("Municipal wastes and other wastes"),
    FURNITURE("Other manufactured goods not elsewhere categorised, goods moved in course of household and office removals"),
    NON_METALLIC_MINERAL_PRODUCT("Other non metallic mineral products"),
    WOOD("Products of wood and cork (except furniture), articles of straw and plaiting materials, pulp, paper and paper products, printed matter and recorded media"),
    TEXTILES("Textile products, leather and leather products"),
    NONE("None of these sectors");

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
