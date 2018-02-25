package org.dvsa.testing.lib.pages.internal.dataretention;

import activesupport.system.out.Output;
import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.enums.dataretention.ActionType;
import org.dvsa.testing.lib.pages.enums.dataretention.DataRetentionRule;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.exception.UnableToFindDataRetentionRule;
import org.jetbrains.annotations.NotNull;

public class RuleAdminPage extends DataRetentionPage {
    // Selector
    private static String RULE_DESCRIPTION_ROW_TEMPLATE = "//*/td/a[contains(text(),'%s')]";
    private static String IS_ENABLED_TEMPLATE_ROW = RULE_DESCRIPTION_ROW_TEMPLATE + "/../../td[5]";
    private static String ACTION_TYPE_TEMPLATE_ROW = RULE_DESCRIPTION_ROW_TEMPLATE + "/../../td[6]";
    private static String NEXT_BUTTON = "//a[contains(text(),'Next')]";

    private static class EditModel {
        private static String IS_ENABLED_NO = "input[name='ruleDetails[isEnabled]']:nth-of-type(1)";
        private static String IS_ENABLED_YES = "input[name='ruleDetails[isEnabled]']:nth-of-type(2)";
        private static String ACTION_TYPE = "select[name='ruleDetails[actionType]']";

        // Added string interpolation so that an attribute checker can be inserted for checking which of
        // the options are the selected one.
        private static String ACTION_TYPE_AUTOMATE_TEMPLATE = ACTION_TYPE + " option%s:nth-of-type(1)";
        private static String ACTION_TYPE_REVIEW_TEMPLATE = ACTION_TYPE + " option%s:nth-of-type(2)";

        private static String SELECTED = ".selected ";

        public static void enable(boolean enable) throws UninitialisedDriverException {
            if (enable) {
                EditModel.enable();
            } else {
                EditModel.disable();
            }
        }

        public static void actionType(@NotNull ActionType actionType) throws UninitialisedDriverException {
            switch (actionType) {
                case AUTOMATE:
                    if (isElementNotPresent(String.format(ACTION_TYPE_AUTOMATE_TEMPLATE, "[selected]"))) {
                        click(String.format(ACTION_TYPE_AUTOMATE_TEMPLATE, ""));
                    }
                    break;
                case REVIEW:
                    if (isElementNotPresent(String.format(ACTION_TYPE_REVIEW_TEMPLATE, "[selected]"))) {
                        click(String.format(ACTION_TYPE_REVIEW_TEMPLATE, ""));
                    }
                    break;
                default:
                    throw new IllegalArgumentException(
                            Output.printColoredLog(
                                    "[ERROR] Action type "
                                            + actionType.name()
                                            + " is not allowed, only automate and review are supported action types"
                            )
                    );
            }
        }

        public static void disable() throws UninitialisedDriverException {
            if (!isElementPresent(SELECTED + IS_ENABLED_NO)) {
                click(IS_ENABLED_YES);
            }
        }

        public static void enable() throws UninitialisedDriverException {
            if (!isElementPresent(SELECTED + IS_ENABLED_YES)) {
                click(IS_ENABLED_YES);
            }
        }
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
                click(NEXT_BUTTON, SelectorType.XPATH);
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
