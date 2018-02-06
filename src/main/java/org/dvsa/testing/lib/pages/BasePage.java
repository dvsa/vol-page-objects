package org.dvsa.testing.lib.pages;

import activesupport.system.out.Output;
import activesupport.url.URL;
import org.dvsa.testing.lib.browser.Browser;
import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.exception.ElementDidNotAppearWithinSpecifiedTimeException;
import org.dvsa.testing.lib.pages.exception.ElementDidNotDisappearWithinSpecifiedTimeException;
import org.dvsa.testing.lib.pages.exception.IncorrectPageTitleException;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class BasePage {

    private static String URI;
    private static String SCHEME;
    private static String DOMAIN;

    private static String PAGE_TITLE_SELECTOR = "h1";

    protected static String getScheme() throws UninitialisedDriverException {
        initialiseURLSectionsOnFirstCall();
        return SCHEME;
    }

    protected static String getDomain() throws UninitialisedDriverException {
        initialiseURLSectionsOnFirstCall();
        return DOMAIN;
    }

    private static void initialiseURLSectionsOnFirstCall() throws UninitialisedDriverException {
        if(URI == null){
            updateURLSections();
        }
    }

    private static void updateURLSections() throws UninitialisedDriverException {
        if (Browser.isInitialised()) {
            URI = Browser.getURL();
            SCHEME = URL.extractScheme(URI);
            DOMAIN = URL.extractDomain(URI);
        }
    }

    protected static String getPageTitleSelector() {
        return PAGE_TITLE_SELECTOR;
    }

    /**
     * This returns any text content that an element possesses.
     * @param selector This should be a CSS or XPATH selector which is used to identify which elements text is to be retrieved.
     * @param selectorType This is the type of selector that the argument selector is.
     * @return The specified elements text contents.
     * @throws UninitialisedDriverException
     */
    protected static String getText(@NotNull String selector, @NotNull SelectorType selectorType) throws UninitialisedDriverException {
        return find(selector, selectorType).getText();
    }

    protected static void enterField(@NotNull String selector, @NotNull String text) throws UninitialisedDriverException {
        find(selector).sendKeys(text);
    }

    /**
     * Clicks on the first element found using the passed in CSS selector.
     * Note: There is an overloaded version of this method that allows you to specify which type of selector
     * you'd like to use, this overloaded version supports both CSS and XPATH.
     * @param selector This should be a css selector.
     * @throws UninitialisedDriverException This is thrown in the event that the driver has not been initialised.
     */
    protected static void click(@NotNull String selector) throws UninitialisedDriverException {
        click(selector, SelectorType.CSS);
    }

    /**
     * Clicks on the first element found using the passed in selector.
     * @param selector This is the selector, this should be either CSS or XPATH.
     * @param selectorType This specifies what type of selector the first argument, selector, is.
     * @throws UninitialisedDriverException
     */
    protected static void click(@NotNull String selector, @NotNull SelectorType selectorType) throws UninitialisedDriverException {
        find(selector, selectorType).click();
    }

    private static WebDriver getDriver() throws UninitialisedDriverException {
        return Browser.getDriver();
    }

    protected static String nameAttribute(@NotNull String element, @NotNull String value){
        return attributeSelector(element, "name", value);
    }

    protected static String attributeSelector(@NotNull String element, @NotNull String attribute, @NotNull String value){
        return String.format("%s[%s=\"%s\"]", element, attribute, value);
    }

    protected static void list(@NotNull String listSelector, int itemPosition) throws UninitialisedDriverException {
        list(listSelector, itemPosition, SelectorType.CSS);
    }

    protected static void list(@NotNull String listSelector, int itemPosition, @NotNull SelectorType selectorType) throws UninitialisedDriverException {
        (new Select(find(listSelector, selectorType))).selectByIndex(itemPosition);
    }

    protected static void list(@NotNull String listSelector, @NotNull String visibleText) throws UninitialisedDriverException {
        list(listSelector, visibleText, SelectorType.CSS);
    }

    protected static void list(@NotNull String listSelector, @NotNull String visibleText , @NotNull SelectorType selectorType) throws UninitialisedDriverException {
        (new Select(find(listSelector, selectorType))).selectByVisibleText(visibleText);
    }

    private static WebElement find(@NotNull String selector) throws UninitialisedDriverException {
        return find(selector, SelectorType.CSS);
    }

    private static WebElement find(@NotNull String selector, @NotNull SelectorType selectorType) throws UninitialisedDriverException {
        By bySelector = by(selector, selectorType);
        return Browser.getDriver().findElement(bySelector);
    }

    private static List<WebElement> findAll(@NotNull String selector, @NotNull SelectorType selectorType) throws UninitialisedDriverException {
        By bySelector = by(selector, selectorType);
        return Browser.getDriver().findElements(bySelector);
    }

    private static By by(@NotNull String selector, @NotNull SelectorType selectorType) {
        By bySelector;

        switch (selectorType) {
            case CSS:
                bySelector = By.cssSelector(selector);
                break;
            case XPATH:
                bySelector = By.xpath(selector);
                break;
            default:
                throw new IllegalArgumentException("Only CSS and XPATH selector types are allowed");
        }
        return bySelector;
    }

    protected static int size(@NotNull String selector, @NotNull SelectorType selectorType) throws UninitialisedDriverException {
        return findAll(selector, selectorType).size();
    }

    protected static boolean isElementNotPresent(@NotNull String selector) throws UninitialisedDriverException {
        return isElementNotPresent(selector, SelectorType.CSS);
    }

    protected static boolean isElementNotPresent(@NotNull String selector, SelectorType selectorType) throws UninitialisedDriverException {
        return !isElementPresent(selector, selectorType);
    }

    protected static boolean isElementPresent(@NotNull String selector) throws UninitialisedDriverException {
        return isElementPresent(selector, SelectorType.CSS);
    }

    protected static boolean isElementPresent(@NotNull String selector, SelectorType selectorType) throws UninitialisedDriverException {
        return find(selector, selectorType) != null;
    }

    protected static void untilElementPresentWithin(@NotNull String selector, long milliseconds) throws UninitialisedDriverException, ElementDidNotAppearWithinSpecifiedTimeException {
        boolean elementFound = isElementPresentWithin(selector, milliseconds);
        if(!elementFound){
            throw new ElementDidNotAppearWithinSpecifiedTimeException(
                    Output.printColoredLog(
                            String.format(
                                    "[ERROR] Element with the selector %s did not appear after %d milliseconds",
                                    selector,
                                    milliseconds
                            )
                    )
            );
        }
    }

    protected static void untilElementNotPresentWithin(@NotNull String selector, long milliseconds) throws ElementDidNotDisappearWithinSpecifiedTimeException, UninitialisedDriverException {
        boolean elementFound = isElementNotPresentWithin(selector, milliseconds);
        if(!elementFound){
            throw new ElementDidNotDisappearWithinSpecifiedTimeException(
                    Output.printColoredLog(
                            String.format(
                                    "[ERROR] Element with the selector %s did not appear after %d milliseconds",
                                    selector,
                                    milliseconds
                            )
                    )
            );
        }
    }

    protected static boolean isElementPresentWithin(@NotNull String selector, long milliseconds) throws UninitialisedDriverException {
        final long WAIT_INTERVAL = 250;
        long count = 0;
        boolean found;

        do {
            found = isElementPresent(selector);

            if(found || count >= milliseconds){
                break;
            }

            try {
                Thread.sleep(WAIT_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            count += WAIT_INTERVAL;

        } while (!found && count <= milliseconds);

        return found;
    }

    protected static boolean isElementNotPresentWithin(@NotNull String selector, long milliseconds) throws UninitialisedDriverException {
        return !isElementPresentWithin(selector, milliseconds);
    }

    protected static boolean isNotSelected(@NotNull String selector) throws UninitialisedDriverException {
        return !isSelected(selector);
    }

    protected static boolean isSelected(@NotNull String selector) throws UninitialisedDriverException {
        return getDriver().findElement(By.cssSelector(selector)).isSelected();
    }

    protected static void select(@NotNull String selector) throws UninitialisedDriverException {
        if(isNotSelected(selector)){
            click(selector);
        }
    }

    protected static void deselect(@NotNull String selector) throws UninitialisedDriverException {
        if(isSelected(selector)){
            click(selector);
        }
    }

    protected static void isNotPresent(@NotNull String selector, int timeToWait) throws UninitialisedDriverException {
        new WebDriverWait(getDriver(), timeToWait).until(not(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(selector))));
    }

    protected static void isPresent(@NotNull String selector, int timeToWait) throws UninitialisedDriverException {
        new WebDriverWait(getDriver(), timeToWait).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(selector)));
    }

    protected static void isNotInDOM(@NotNull String selector, int timeToWait) throws UninitialisedDriverException {
        new WebDriverWait(getDriver(), timeToWait).until(not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(selector))));
    }

    protected static void isInDOM(@NotNull String selector, int timeToWait) throws UninitialisedDriverException {
        new WebDriverWait(getDriver(), timeToWait).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(selector)));
    }

    protected static boolean contains(@NotNull String selector, @NotNull String content) throws UninitialisedDriverException {
        return find(selector).getText().contains(content);
    }

    protected static boolean isExpectedPageTitle( @NotNull String title) throws UninitialisedDriverException {
        return isExpectedPageTitle(getPageTitleSelector(), title);
    }

    protected static boolean isExpectedPageTitle(@NotNull String title, long horizonMilliseconds) throws UninitialisedDriverException {
        return isExpectedPageTitle(getPageTitleSelector(), title, horizonMilliseconds);
    }

    protected static boolean isExpectedPageTitle(@NotNull String selector, @NotNull String title) throws UninitialisedDriverException {
        long horizonMilliseconds = 3000;
        return isExpectedPageTitle(selector, title, horizonMilliseconds);
    }

    protected static boolean isExpectedPageTitle(@NotNull String selector, @NotNull String title, long horizonMilliseconds) throws UninitialisedDriverException {
        boolean isCurrentPage = false;

        if(isElementPresentWithin(selector, horizonMilliseconds)){
            isCurrentPage = contains(selector, title);
        }

        return isCurrentPage;
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String title) throws UninitialisedDriverException {
        return isNotExpectedPageTitle(getPageTitleSelector(), title);
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String title, long horizonMilliseconds) throws UninitialisedDriverException {
        return isNotExpectedPageTitle(getPageTitleSelector(), title, horizonMilliseconds);
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String selector, @NotNull String title) throws UninitialisedDriverException {
        long horizonMilliseconds = 3000;
        return isNotExpectedPageTitle(selector, title, horizonMilliseconds);
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String selector, @NotNull String title, long horizonMilliseconds) throws UninitialisedDriverException {
        return !isExpectedPageTitle(selector, title, horizonMilliseconds);
    }

    protected static void untilExpectedPageTitle(@NotNull String selector, @NotNull String pageTitle) throws IncorrectPageTitleException, UninitialisedDriverException {
        long horizonMilliseconds = 3000;
        untilExpectedPageTitle(selector, pageTitle, horizonMilliseconds);
    }

    protected static void untilExpectedPageTitle(@NotNull String pageTitle) throws IncorrectPageTitleException, UninitialisedDriverException {
        long horizonMilliseconds = 3000;
        untilExpectedPageTitle(pageTitle, horizonMilliseconds);
    }

    protected static void untilExpectedPageTitle(@NotNull String pageTitle, long horizonMilliseconds) throws IncorrectPageTitleException, UninitialisedDriverException {
        untilExpectedPageTitle(PAGE_TITLE_SELECTOR, pageTitle, horizonMilliseconds);
    }

    protected static void untilExpectedPageTitle(@NotNull String selector, @NotNull String pageTitle, long horizonMilliseconds) throws IncorrectPageTitleException, UninitialisedDriverException {
        if(!isExpectedPageTitle(selector, pageTitle, horizonMilliseconds)){
            throw new IncorrectPageTitleException(String.format("[ERROR] The page title for the current page should be %s but was %s", pageTitle, Browser.getPageTitle()));
        }
    }

    protected static void untilNotExpectedPageTitle(@NotNull String selector, @NotNull String pageTitle) throws IncorrectPageTitleException, UninitialisedDriverException {
        long horizonMilliseconds = 3000;
        untilNotExpectedPageTitle(selector, pageTitle, horizonMilliseconds);
    }

    protected static void untilNotExpectedPageTitle(@NotNull String pageTitle) throws IncorrectPageTitleException, UninitialisedDriverException {
        long horizonMilliseconds = 3000;
        untilNotExpectedPageTitle(pageTitle, horizonMilliseconds);
    }

    protected static void untilNotExpectedPageTitle(@NotNull String pageTitle, long horizonMilliseconds) throws IncorrectPageTitleException, UninitialisedDriverException {
        untilNotExpectedPageTitle(PAGE_TITLE_SELECTOR, pageTitle, horizonMilliseconds);
    }

    protected static void untilNotExpectedPageTitle(@NotNull String selector, @NotNull String pageTitle, long horizonMilliseconds) throws UninitialisedDriverException, IncorrectPageTitleException {
        if(!isNotExpectedPageTitle(selector, pageTitle, horizonMilliseconds)){
            throw new IncorrectPageTitleException(String.format("[ERROR] The page title for the page did not change asfter %d milliseconds", horizonMilliseconds));
        }
    }

}
