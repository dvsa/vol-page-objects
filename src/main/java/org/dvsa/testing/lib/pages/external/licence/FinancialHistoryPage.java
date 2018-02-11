package org.dvsa.testing.lib.pages.external.licence;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;

public class FinancialHistoryPage extends BasePage {
    // Selector
    private static String NTH_QUESTION_TEMPLATE = "fieldset > fieldset:nth-of-type(%d) label:nth-of-type(%d) input";

    private static String DECLARED_BANKRUPT_TEMPLATE = String.format(NTH_QUESTION_TEMPLATE, 1);
    private static String INVOLVED_WITH_A_BUSINESS_GONE_INTO_LIQUIDATION_TEMPLATE = String.format(NTH_QUESTION_TEMPLATE, 2);
    private static String INVOLVED_WITH_BUSINESS_IN_RECEIVERSHIP = String.format(NTH_QUESTION_TEMPLATE, 3);
    private static String INVOLVED_WITH_COMPANY_GONE_INTO_ADMINISTRATION_OR_VOLUNTARY_AGREEMENT = String.format(NTH_QUESTION_TEMPLATE, 4);
    private static String DISQUALIFIED_THROUGH_COMPANY_DIRECTORS_DISQUALIFIED_ACT_1986 = String.format(NTH_QUESTION_TEMPLATE, 5);

    private static String AGREEMENT_TO_TERMS_AND_CONDITIONS = "input[type='checkbox']";

    public static void declaredBankrupctyInThePast(boolean hasBeenBankruptBefore) throws UninitialisedDriverException {
        click(String.format(DECLARED_BANKRUPT_TEMPLATE, radioPosition(hasBeenBankruptBefore)));
    }

    public static void InvolvedWithBusinessGoneIntoLiquidation(boolean involved) throws UninitialisedDriverException {
        click(String.format(INVOLVED_WITH_A_BUSINESS_GONE_INTO_LIQUIDATION_TEMPLATE, radioPosition(involved)));
    }

    public static void involvedWithBusinessInReceivership(boolean involved) throws UninitialisedDriverException {
        click(String.format(INVOLVED_WITH_BUSINESS_IN_RECEIVERSHIP, radioPosition(involved)));
    }

    public static void involvedWithCompanyGoneIntoAdministrationOrVoluntaryAgreement(boolean involved) throws UninitialisedDriverException {
        click(String.format(INVOLVED_WITH_COMPANY_GONE_INTO_ADMINISTRATION_OR_VOLUNTARY_AGREEMENT, radioPosition(involved)));
    }

    public static void disqualifiedThroughCompanyDirectorsDisqualifiedAct1986(boolean disqualified) throws UninitialisedDriverException {
        click(String.format(DISQUALIFIED_THROUGH_COMPANY_DIRECTORS_DISQUALIFIED_ACT_1986, radioPosition(disqualified)));
    }

    private static int radioPosition(boolean option) {
        return option ? 1 : 2;
    }

}