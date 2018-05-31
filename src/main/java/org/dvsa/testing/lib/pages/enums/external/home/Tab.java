package org.dvsa.testing.lib.pages.enums.external.home;

import org.jetbrains.annotations.NotNull;

public enum Tab {
    LICENCES("Licences"),
    FEES("Fees"),
    DOCUMENTS("Documents"),
    PERMITS("Permits");

    private String title;

    Tab(@NotNull String title) {
        this.title = title;
    }

    @Override
    public String toString(){
        return this.title;
    }

}
