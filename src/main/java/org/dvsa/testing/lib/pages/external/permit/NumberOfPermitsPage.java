package org.dvsa.testing.lib.pages.external.permit;

public class NumberOfPermitsPage extends BasePermitPage {

    private static String NUMBER_OF_REQUIRED_PERMITS = "input[type=number]#PermitsRequired";

    public static void quantity(int number){
        scrollAndEnterField(NUMBER_OF_REQUIRED_PERMITS, String.valueOf(number));
    }

}
