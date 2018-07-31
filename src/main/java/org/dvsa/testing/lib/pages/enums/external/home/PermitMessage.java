package org.dvsa.testing.lib.pages.enums.external.home;

public enum PermitMessage {
    // TODO: Find out message that's displayed when there aren't any ongoing permits
    ONGOING(""),
    CURRENT("You don't have any active Permits");

    String title;

    PermitMessage(String title){
        this.title = title;
    }

    @Override
    public String toString(){
        return title;
    }
}
