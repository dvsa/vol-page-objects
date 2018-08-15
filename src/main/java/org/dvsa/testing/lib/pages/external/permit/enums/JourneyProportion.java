package org.dvsa.testing.lib.pages.external.permit.enums;

import activesupport.number.Int;

public enum JourneyProportion {

    LessThan60Percent(1),
    From60To90Percent(2),
    MoreThan90Percent(3);

    private int index;

    JourneyProportion(int index){
        this.index = index;
    }

    public static JourneyProportion random(){
        int index = Int.random(0, JourneyProportion.values().length - 1);
        return JourneyProportion.values()[index];
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }
}
