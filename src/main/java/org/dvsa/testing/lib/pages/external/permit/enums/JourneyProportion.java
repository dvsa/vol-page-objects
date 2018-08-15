package org.dvsa.testing.lib.pages.external.permit.enums;

import activesupport.number.Int;

public enum JourneyProportion {

    LessThan60Percent,
    From60To90Percent,
    MoreThan90Percent;

    public static JourneyProportion random(){
        int index = Int.random(0, JourneyProportion.values().length - 1);
        return JourneyProportion.values()[index];
    }

}
