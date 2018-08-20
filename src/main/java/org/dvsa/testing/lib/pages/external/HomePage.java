package org.dvsa.testing.lib.pages.external;

import org.apache.commons.lang3.StringUtils;
import org.dvsa.testing.lib.PermitApplication;
import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.enums.external.home.PermitMessage;
import org.dvsa.testing.lib.pages.enums.external.home.Tab;
import org.dvsa.testing.lib.pages.exception.ElementDidNotAppearWithinSpecifiedTimeException;
import org.dvsa.testing.lib.pages.exception.FoundElementException;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HomePage extends BasePage {
    // Selectors
    private static String APPLY_FOR_LICENCE_BUTTON = "//*/a[contains(text(), 'Apply for a')]";
    private static String APPLY = "//a[contains(text(), 'Apply for a')]";

    private static String TAB_TEMPLATE = "//*/ul[@class='tab-list']//a[contains(text(), '%s')]";

    public static class PermitsTab {

        private static String TABLE_ROW = "//tbody//tr";
        private static String REFERENCE_NUMBER = ".//td[@data-heading='Reference number']//span";
        private static String NO_OF_PERMITS = ".//td[@data-heading='No of permits']";
        private static String TYPE = ".//td[@data-heading='Type']";
        private static String STATUS = ".//td[@data-heading='Status']//span";

        // Attributes
        final public static String RESOURCE = "/permits";

        public void apply() {
            click(APPLY);
        }

        public static void isPermitMessagePresent(PermitMessage permitMessage){
            if (!BasePage.isElementPresent(String.format("//*[]contains(text(),'%s')", permitMessage.toString()))){
                throw new FoundElementException("Permit message '" + permitMessage.toString() + "' should be present");
            }
        }

        public static List<PermitApplication> getOngoingPermitApplications() {
            List<WebElement> rows = findAll(TABLE_ROW, SelectorType.XPATH);
            return rows.stream().map((el) -> {
                String numOfPermitsText = el.findElement(By.xpath(NO_OF_PERMITS)).getText();
                Integer numOfPermits = StringUtils.isBlank(numOfPermitsText) ? null : Integer.valueOf(numOfPermitsText);

                return new PermitApplication()
                    .withReferenceNumber(el.findElement(By.xpath(REFERENCE_NUMBER)).getText())
                    .withNoOfPermits(numOfPermits)
                    .withType(el.findElement(By.xpath(TYPE)).getText())
                    .withStatus(el.findElement(By.xpath(STATUS)).getText());
            }
            ).collect(Collectors.toList());
        }

    }

    // Attributes
    private static String PAGE_TITLE_TEXT = "Home";
    private static String RESOURCE_PATH = "dashboard/";

    // Behaviour
    public static void selectTab(Tab tab) {
        click(String.format(TAB_TEMPLATE, tab.toString()), SelectorType.XPATH);
    }

    public static void applyForLicenceButton() throws UninitialisedDriverException {
        scrollAndClick(APPLY_FOR_LICENCE_BUTTON, SelectorType.XPATH);
    }

    // Validations
    public static void untilOnPage() throws UninitialisedDriverException, ElementDidNotAppearWithinSpecifiedTimeException {
        untilElementPresent(APPLY_FOR_LICENCE_BUTTON, SelectorType.XPATH);
    }

    public static void tabIsPresent(Tab tab){
        String selector = String.format(TAB_TEMPLATE, tab.toString());
        untilElementIsPresent(selector, SelectorType.XPATH, 30, TimeUnit.SECONDS);
    }

    public static void tabIsNotPresent(Tab tab){
        String selector = String.format(TAB_TEMPLATE, tab.toString());
        elementIsNotPresent(selector, SelectorType.XPATH);
    }

    public static boolean hasReferenceNumber(@NotNull String referenceNumber){
        return isTextPresent(referenceNumber);
    }

}
