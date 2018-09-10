package org.dvsa.testing.lib.pages.external.permit;

import activesupport.string.Str;

public class NumberOfPermitsPage extends BasePermitPage {

    private static String NUMBER_OF_REQUIRED_PERMITS = "input[type=number]#PermitsRequired";

    private static String NUMBER_OF_AUTHORISED_PERMITS = "#PermitsRequired p";
    private static String NUMBER_OF_AUTHORISED_PERMITS_REGEX = "(?<=Up to )\\d+";

    final public static String RESOURCE = "ecmt-no-of-permits/";

    public static void quantity(int number){
        scrollAndEnterField(NUMBER_OF_REQUIRED_PERMITS, String.valueOf(number));
    }

    public static int getNumberOfPermitsAuthorised() {
        String subject = getText(NUMBER_OF_AUTHORISED_PERMITS);
        return Integer.parseInt(Str.find(NUMBER_OF_AUTHORISED_PERMITS_REGEX, subject));
    }

}
