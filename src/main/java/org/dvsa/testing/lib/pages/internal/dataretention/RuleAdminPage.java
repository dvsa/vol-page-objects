package org.dvsa.testing.lib.pages.internal.dataretention;

import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.enums.DataRetentionRule;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.exception.UnableToFindDataRetentionRule;
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

    /**
     * Finds and selects the specified data retention rule. This method will go through the various pagination while
     * trying to find the data retention rule.
     * @param dataRetentionRule This is the that the user wishes to select.
     * @throws UninitialisedDriverException is thrown if the driver is null, hasn't been initialised,  or has been closed
     * @throws UnableToFindDataRetentionRule is thrown if the specified rule is not present in any of the pagination.
     */
    public static void selectRule(@NotNull DataRetentionRule dataRetentionRule) throws UninitialisedDriverException, UnableToFindDataRetentionRule {
        String ruleSelector = String.format(RULE_DESCRIPTION_ROW_TEMPLATE, dataRetentionRule);
        boolean foundDataRetentionRule = false;

        do {
            if (isElementPresent(ruleSelector, SelectorType.XPATH)) {
                foundDataRetentionRule = true;
                click(ruleSelector, SelectorType.XPATH);
            } else {
                click(NEXT_BUTTON);
            }
        } while (!isElementPresent(ruleSelector, SelectorType.XPATH) && isElementPresent(NEXT_BUTTON, SelectorType.XPATH));

        if (!foundDataRetentionRule) {
            throw new UnableToFindDataRetentionRule("Unable to find the data retention rule '"
                    + dataRetentionRule.name()
                    + "' from the Rule Admin page (including pagination)"
            );
        }
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
