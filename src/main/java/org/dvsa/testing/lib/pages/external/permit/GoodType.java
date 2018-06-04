package org.dvsa.testing.lib.pages.external.permit;

import org.jetbrains.annotations.NotNull;

public enum GoodType {

    FOOD("Food products"),
    MAIL_AND_PARCELS("Mail and parcels"),
    TRANSPORT_EQUIPMENT("Transport equipment"),
    METAL("Metal"),
    CHEMICALS("Chemicals"),
    NON_METALLIC_MINERAL_PRODUCT("Non-Metallic mineral product"),
    WOOD("Wood"),
    FURNITURE("Furniture"),
    RAW_MATERIALS("Raw materials"),
    COKE_AND_REFINED_PETROLEUM_PRODUCTS("Coke and refined petroleum products"),
    TEXTILES("Textiles"),
    OTHER("Other");

    private String textIdentifier;

    GoodType (@NotNull String textIdentifier) {
        this.textIdentifier = textIdentifier;
    }

    @Override
    public String toString() {
        return textIdentifier;
    }

}
