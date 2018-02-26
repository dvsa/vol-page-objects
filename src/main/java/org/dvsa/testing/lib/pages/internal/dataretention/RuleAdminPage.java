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

    public static class EditModel {

        private static String IS_ENABLED_NO_TEMPLATE = "fieldset.inline label%s:nth-of-type(1) input";
        private static String IS_ENABLED_YES_TEMPLATE = "fieldset.inline label%s:nth-of-type(2) input";
        private static String ACTION_TYPE = "select[name='ruleDetails[actionType]']";

        private static String SAVE = "//*/button[contains(text(), 'Save')]";
        private static String CANCEL = "//*/button[contains(text(), 'Cancel')]";

        // Added string interpolation so that an attribute checker can be inserted for checking which of
        // the options is selected.
        private static String ACTION_TYPE_AUTOMATE_TEMPLATE = ACTION_TYPE + " option%s:nth-of-type(1)";
        private static String ACTION_TYPE_REVIEW_TEMPLATE = ACTION_TYPE + " option%s:nth-of-type(2)";

        private static String SELECTED = ".selected ";

        // Attributes
        public static String MAIN_TITLE = "Edit Data retention rule";

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
            if (!isElementPresent(String.format(IS_ENABLED_NO_TEMPLATE, SELECTED))) {
                click(String.format(IS_ENABLED_YES_TEMPLATE, ""));
            }
        }

        public static void enable() throws UninitialisedDriverException {
            if (!isElementPresent(String.format(IS_ENABLED_YES_TEMPLATE, SELECTED))) {
                click(String.format(IS_ENABLED_YES_TEMPLATE, ""));
            }
        }

        public static void save() throws UninitialisedDriverException {
            click(SAVE, SelectorType.XPATH);
        }

        public static void cancel() throws UninitialisedDriverException {
            click(CANCEL, SelectorType.XPATH);
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
        String ruleSelector = String.format(RULE_DESCRIPTION_ROW_TEMPLATE, dataRetentionRule.toString());

        pagenateToRule(ruleSelector, SelectorType.XPATH, 2);

        scrollTo(ruleSelector, SelectorType.XPATH);
        click(ruleSelector, SelectorType.XPATH);
        untilExpectedTextInElement("h1", EditModel.MAIN_TITLE, 5); // Waits until the edit model has appeared
    }

    private static void pagenateToRule(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) throws UninitialisedDriverException, UnableToFindDataRetentionRule {
        boolean foundDataRetentionRule = false;

        do {
            if (isInDOM(selector, selectorType, seconds)) {
                foundDataRetentionRule = true;
                break;
            } else if (isInDOM(NEXT_BUTTON, SelectorType.XPATH)){
                click(NEXT_BUTTON, SelectorType.XPATH);
            } else if (isNotInDOM(NEXT_BUTTON, SelectorType.XPATH)) {
                break;
            }
        } while (true);

        if (!foundDataRetentionRule) {
            throw new UnableToFindDataRetentionRule("Unable to find the element with the selector '"
                    + selector
                    + "' after pagenation"
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
