package org.dvsa.testing.lib.pages.internal.dataretention;

import activesupport.system.out.Output;
import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.enums.dataretention.ActionType;
import org.dvsa.testing.lib.pages.enums.dataretention.DataRetentionRule;
import org.jetbrains.annotations.NotNull;

public class RuleAdminPage extends DataRetentionPage {
    // Selector
    private static String RULE_DESCRIPTION_ROW_TEMPLATE = "//*/td/a[contains(text(),'%s')]";
    private static String IS_ENABLED_TEMPLATE_ROW = RULE_DESCRIPTION_ROW_TEMPLATE + "/../../td[5]";
    private static String ACTION_TYPE_TEMPLATE_ROW = RULE_DESCRIPTION_ROW_TEMPLATE + "/../../td[6]";
    private static String NEXT_BUTTON = "//a[contains(text(),'Next')]";

    // Attributes
    private static String MAIN_TITLE = "Data retention rules";

    public static class EditModel {

        private static String RULE_ID = "input[name='ruleDetails[id]']";
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
                    if (isElementNotPresent(String.format(ACTION_TYPE_AUTOMATE_TEMPLATE, "[selected]"),SelectorType.CSS)); {
                        click(String.format(ACTION_TYPE_AUTOMATE_TEMPLATE, ""));
                    }
                    break;
                case REVIEW:
                    if (isElementNotPresent(String.format(ACTION_TYPE_REVIEW_TEMPLATE, "[selected]"),SelectorType.CSS)) {
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

        public static String extractRuleID() throws UninitialisedDriverException {
            return getAttribute(RULE_ID, "value");
        }

        public static void untilOnModel() throws UninitialisedDriverException {
            RuleAdminPage.untilExpectedTextInElement("h1", RuleAdminPage.EditModel.MAIN_TITLE, 5);
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

    public static void untilOnPage() throws UninitialisedDriverException {
        untilExpectedTextInElement(MAIN_TITLE_SELECTOR, MAIN_TITLE, 5);
    }

}
