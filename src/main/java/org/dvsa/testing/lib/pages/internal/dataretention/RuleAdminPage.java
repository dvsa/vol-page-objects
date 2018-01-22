package org.dvsa.testing.lib.pages.internal.dataretention;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.enums.DataRetentionRule;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.jetbrains.annotations.NotNull;

public class RuleAdminPage extends DataRetentionPage {
    // Selector
    private static String RULE_DESCRIPTION_ROW_TEMPLATE = "//*/td/a[contains(text(),'%s')]";
    private static String IS_ENABLED_TEMPLATE_ROW = RULE_DESCRIPTION_ROW_TEMPLATE + "/../../td[5]";
    private static String ACTION_TYPE_TEMPLATE_ROW = RULE_DESCRIPTION_ROW_TEMPLATE + "/../../td[6]";
    private static String NEXT_BUTTON = "//a[contains(text(),'Next')]";

    // Attributes
    private class EditModel {

    }

    public static void selectRule(@NotNull DataRetentionRule dataRetentionRule) throws UninitialisedDriverException {
        String ruleSelector = String.format(RULE_DESCRIPTION_ROW_TEMPLATE, dataRetentionRule);

        do {
            if (isElementPresent(ruleSelector, SelectorType.XPATH)) {
                click(ruleSelector, SelectorType.XPATH);
            }
        } while (!isElementPresent(ruleSelector, SelectorType.XPATH) && );
    }

    public static String getActionType(@NotNull DataRetentionRule dataRetentionRule) throws UninitialisedDriverException {
        return getRelativeRuleCell(ACTION_TYPE_TEMPLATE_ROW, dataRetentionRule);
    }

    public static String getIsEnabled(@NotNull DataRetentionRule dataRetentionRule) throws UninitialisedDriverException {
        return getRelativeRuleCell(IS_ENABLED_TEMPLATE_ROW, dataRetentionRule);
    }

    public static String getRelativeRuleCell(@NotNull String selector, @NotNull DataRetentionRule dataRetentionRule) throws UninitialisedDriverException {
        return getText(String.format(selector, dataRetentionRule), SelectorType.XPATH);
    }

}
