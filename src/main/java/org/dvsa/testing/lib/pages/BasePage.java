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
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public abstract class BasePage {

    protected static final int WAIT_TIME_SECONDS =  10;

    private static String URI;
    private static String SCHEME;
    private static String DOMAIN;

    protected static String MAIN_TITLE_SELECTOR = "h1";

    protected static String getScheme() {
        initialiseURLSectionsOnFirstCall();
        return SCHEME;
    }

    protected static String getDomain() {
        initialiseURLSectionsOnFirstCall();
        return DOMAIN;
    }

    private static void initialiseURLSectionsOnFirstCall() {
        if(URI == null){
            updateURLSections();
        }
    }

    private static void updateURLSections() {
        if (Browser.isInitialised()) {
            URI = Browser.getURL();
            SCHEME = URL.extractScheme(URI);
            DOMAIN = URL.extractDomain(URI);
        }
    }

    protected static String getMainTitleSelector() {
        return MAIN_TITLE_SELECTOR;
    }

    protected static String getAttribute(@NotNull String selector, @NotNull String attribute) {
        return getAttribute(selector, SelectorType.CSS, attribute);
    }

    protected static String getAttribute(@NotNull String selector, @NotNull SelectorType selectorType, @NotNull String attribute) {
        return find(selector, selectorType).getAttribute(attribute);
    }

    /**
     * This returns any text content that an element possesses.
     * @param selector This should be a CSS or XPATH selector which is used to identify which elements text is to be retrieved.
     * @param selectorType This is the type of selector that the argument selector is.
     * @return The specified elements text contents.
     */
    protected static String getText(@NotNull String selector, @NotNull SelectorType selectorType) {
        return find(selector, selectorType).getText();
    }

    protected static String getText(@NotNull String selector) {
        return getText(selector, SelectorType.CSS);
    }

    protected static boolean hasText(@NotNull String selector, @NotNull SelectorType selectorType, @NotNull String text) {
        return getText(selector, selectorType).equals(text);
    }

    protected static boolean hasText(@NotNull String selector, @NotNull String text) {
        SelectorType selectorType = SelectorType.CSS;
        return hasText(selector, selectorType, text);
    }

    protected static List<String> getListValues(@NotNull String listSelector) {
        List<String> optionValues  = new LinkedList<>();

        Select select = new Select(find(listSelector));
        for (WebElement option : select.getOptions()) {
            optionValues.add(option.getText());
        }

        return optionValues;
    }

    /**
     * Enters text in the specified text input/textarea field found using the specified selector.
     * @param selector The text input field/textarea that's to have text entered in it.
     * @param text The text to be entered.
     */
    protected static void enterField(@NotNull String selector, @NotNull String text) {
        enterField(selector, text, false);
    }

    /**
     * Enters text in the specified text input/textarea field found using the specified selector.
     * @param selector The text input field/textarea that's to have text entered in it.
     * @param text The text to be entered.
     * @param append Specified weather the input field/textarea should be cleared before entering the text.
     */
    protected static void enterField(@NotNull String selector, @NotNull String text, boolean append) {
        WebElement element = find(selector);

        if (!append) {
            element.clear();
        }

        element.sendKeys(text);
    }

    /**
     * Clicks on the first element found using the passed in CSS selector.
     * Note: There is an overloaded version of this method that allows you to specify which type of selector
     * you'd like to use, this overloaded version supports both CSS and XPATH.
     * @param selector This should be a css selector.
     */
    protected static void click(@NotNull String selector) {
        click(selector, SelectorType.CSS);
    }

    /**
     * Clicks on the first element found using the passed in selector.
     * @param selector This is the selector, this should be either CSS or XPATH.
     * @param selectorType This specifies what type of selector the first argument, selector, is.
     */
    protected static void click(@NotNull String selector, @NotNull SelectorType selectorType) {
        find(selector, selectorType).click();
    }

    protected static void scrollAndClick(@NotNull String selector, @NotNull SelectorType selectorType) {
        new Actions(getDriver())
                .moveToElement(find(selector, selectorType))
                .click()
                .perform();
    }

    protected static void scrollAndClick(@NotNull String selector) {
        scrollAndClick(selector, SelectorType.CSS);
    }

    protected static void moveTo(@NotNull String selector, @NotNull SelectorType selectorType) {
        new Actions(getDriver())
                .moveToElement(find(selector, selectorType))
                .perform();
    }

    protected static void scrollTo(@NotNull String selector, @NotNull SelectorType selectorType) {
        scrollTo(selector, selectorType, WAIT_TIME_SECONDS);
    }

    protected static void scrollTo(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) {
        scrollTo(selector, selectorType, (long) seconds, TimeUnit.SECONDS);
    }

    protected static void scrollTo(@NotNull String selector, @NotNull SelectorType selectorType, long duration, TimeUnit timeUnit) {
        WebElement element = find(selector, selectorType);

        // Scrolls to element using JS
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);

        // This will wait the specified amount of time until the element is in view
        untilPresent(selector, selectorType, duration, timeUnit);
    }

    private static WebDriver getDriver() {
        return Browser.getDriver();
    }

    protected static String nameAttribute(@NotNull String element, @NotNull String value){
        return attributeSelector(element, "name", value);
    }

    protected static String attributeSelector(@NotNull String element, @NotNull String attribute, @NotNull String value){
        return String.format("%s[%s=\"%s\"]", element, attribute, value);
    }

    protected static void list(@NotNull String listSelector, int itemPosition) {
        list(listSelector, itemPosition, SelectorType.CSS);
    }

    protected static void list(@NotNull String listSelector, int itemPosition, @NotNull SelectorType selectorType) {
        (new Select(find(listSelector, selectorType))).selectByIndex(itemPosition);
    }

    protected static void list(@NotNull String listSelector, @NotNull String visibleText) {
        list(listSelector, visibleText, SelectorType.CSS);
    }

    protected static void list(@NotNull String listSelector, @NotNull String visibleText , @NotNull SelectorType selectorType) {
        (new Select(find(listSelector, selectorType))).selectByVisibleText(visibleText);
    }

    private static WebElement find(@NotNull String selector) {
        return find(selector, SelectorType.CSS);
    }

    private static WebElement find(@NotNull String selector, @NotNull SelectorType selectorType) {
        By bySelector = by(selector, selectorType);
        return Browser.getDriver().findElement(bySelector);
    }

    private static List<WebElement> findAll(@NotNull String selector, @NotNull SelectorType selectorType) {
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

    protected static int size(@NotNull String selector, @NotNull SelectorType selectorType) {
        return findAll(selector, selectorType).size();
    }

    protected static int size(@NotNull String selector) {
        return size(selector, SelectorType.CSS);
    }

    protected static boolean isElementNotPresent(@NotNull String selector) {
        return isElementNotPresent(selector, SelectorType.CSS);
    }

    protected static boolean isElementNotPresent(@NotNull String selector, SelectorType selectorType) {
        return !isElementPresent(selector, selectorType);
    }

    protected static boolean isElementPresent(@NotNull String selector) {
        return isElementPresent(selector, SelectorType.CSS);
    }

    protected static boolean isElementPresent(@NotNull String selector, SelectorType selectorType) {
        boolean isElementPresent = true;

        try {
            find(selector, selectorType);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            isElementPresent = false;
        }

        return  isElementPresent;
    }

    public static void untilElementPresent(@NotNull String selector) throws ElementDidNotAppearWithinSpecifiedTimeException {
        untilElementPresentWithin(selector, WAIT_TIME_SECONDS);
    }

    public static void untilElementPresent(@NotNull String selector, @NotNull SelectorType selectorType) throws ElementDidNotAppearWithinSpecifiedTimeException {
        untilElementPresentWithin(selector, selectorType, WAIT_TIME_SECONDS);
    }

    public static void untilElementPresentWithin(@NotNull String selector, int seconds) throws ElementDidNotAppearWithinSpecifiedTimeException {
        untilElementPresentWithin(selector, SelectorType.CSS, seconds);
    }

    public static void untilElementPresentWithin(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) throws ElementDidNotAppearWithinSpecifiedTimeException {
        boolean elementFound = isElementPresentWithin(selector, selectorType, seconds);
        if(!elementFound){
            throw new ElementDidNotAppearWithinSpecifiedTimeException(
                    Output.printColoredLog(
                            String.format(
                                    "[ERROR] Element with the selector %s did not appear after %d seconds",
                                    selector,
                                    seconds
                            )
                    )
            );
        }
    }

    public static void untilElementNotPresentWithin(@NotNull String selector, int seconds) throws ElementDidNotDisappearWithinSpecifiedTimeException, UninitialisedDriverException {
        boolean elementFound = isElementNotPresentWithin(selector, seconds);
        if(elementFound){
            throw new ElementDidNotDisappearWithinSpecifiedTimeException(
                    Output.printColoredLog(
                            String.format(
                                    "[ERROR] Element with the selector '%s' did not disappear after %d seconds",
                                    selector,
                                    seconds
                            )
                    )
            );
        }
    }

    protected static boolean isElementPresentWithin(@NotNull String selector, int seconds) {
        return isElementPresentWithin(selector, SelectorType.CSS, seconds);
    }

    @Deprecated
    protected static boolean isElementPresentWithin(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) {
        return isInDOM(selector, selectorType, seconds);
    }

    protected static boolean isElementNotPresentWithin(@NotNull String selector, int seconds) {
        return !isElementPresentWithin(selector, seconds);
    }

    protected static boolean isNotSelected(@NotNull String selector) {
        return !isSelected(selector);
    }

    protected static boolean isSelected(@NotNull String selector) {
        return getDriver().findElement(By.cssSelector(selector)).isSelected();
    }

    protected static void select(@NotNull String selector) {
        if(isNotSelected(selector)){
            click(selector);
        }
    }

    protected static void deselect(@NotNull String selector) {
        if(isSelected(selector)){
            click(selector);
        }
    }

    protected static boolean isNotPresent(@NotNull String selector, int seconds) {
        return isNotPresent(selector, SelectorType.CSS, seconds);
    }

    protected static boolean isNotPresent(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) {
        boolean isNotPresent = true;

        try {
            untilNotPresent(selector, selectorType, seconds);
        } catch (org.openqa.selenium.TimeoutException e) {
            isNotPresent = false;
        }

        return isNotPresent;
    }

    public static void untilNotPresent(@NotNull String selector, int seconds) {
        untilNotPresent(selector, SelectorType.CSS, seconds);
    }

    public static void untilNotPresent(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) {
        new WebDriverWait(getDriver(), seconds).until(not(ExpectedConditions.visibilityOfAllElementsLocatedBy(by(selector,selectorType))));
    }

    protected static boolean isPresent(@NotNull String selector, int seconds) {
        return isPresent(selector, SelectorType.CSS, seconds);
    }

    protected static boolean isPresent(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) {
        boolean isPresent = true;

        try {
            untilPresent(selector, selectorType, seconds);
        } catch (org.openqa.selenium.TimeoutException e) {
            isPresent = false;
        }

        return isPresent;
    }

    public static void untilPresent(@NotNull String selector, int seconds) {
       untilPresent(selector, SelectorType.CSS, seconds);
    }

    public static void untilPresent(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) {
        By by = by(selector, selectorType);
        new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        untilPresent(selector, selectorType, (long) seconds, TimeUnit.SECONDS);
    }

    public static void untilPresent(@NotNull String selector, @NotNull SelectorType selectorType, long duration, TimeUnit timeUnit) {
        By by = by(selector, selectorType);

        Wait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(duration, timeUnit)
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    protected static boolean isNotInDOM(@NotNull String selector, int seconds) {
        return isNotInDOM(selector, SelectorType.CSS, seconds);
    }

    protected static boolean isNotInDOM(@NotNull String selector, @NotNull SelectorType selectorType) {
        return isInDOM(selector, selectorType, WAIT_TIME_SECONDS);
    }

    protected static boolean isNotInDOM(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) {
        boolean isNotInDOM = true;

        try {
            untilInDOM(selector, selectorType, seconds);
        } catch (org.openqa.selenium.TimeoutException e) {
            isNotInDOM = false;
        }

        return isNotInDOM;
    }

    public static void untilNotInDOM(@NotNull String selector, int seconds) {
        untilNotInDOM(selector, SelectorType.CSS, seconds);
    }

    public static void untilNotInDOM(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) {
        new WebDriverWait(getDriver(), seconds).until(not(ExpectedConditions.presenceOfAllElementsLocatedBy(by(selector, selectorType))));
    }

    protected static boolean isInDOM(@NotNull String selector, int seconds) {
        return isInDOM(selector, SelectorType.CSS, seconds);
    }

    protected static boolean isInDOM(@NotNull String selector, @NotNull SelectorType selectorType) {
        return isInDOM(selector, selectorType, WAIT_TIME_SECONDS);
    }

    protected static boolean isInDOM(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) {
        boolean isInDOM = true;

        try {
            untilInDOM(selector, selectorType, seconds);
        } catch (org.openqa.selenium.TimeoutException e) {
            isInDOM = false;
        }

        return isInDOM;
    }

    public static void untilInDOM(@NotNull String selector, int seconds) {
        untilInDOM(selector, SelectorType.CSS, seconds);
    }

    public static void untilInDOM(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) {
        By by = by(selector, selectorType);
        new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected static boolean contains(@NotNull String selector, @NotNull String content) {
        return find(selector).getText().contains(content);
    }

    protected static boolean isExpectedPageTitle( @NotNull String title) {
        return isExpectedPageTitle(getMainTitleSelector(), title);
    }

    protected static boolean isExpectedPageTitle(@NotNull String title, int horizonSeconds) {
        return isExpectedPageTitle(getMainTitleSelector(), title, horizonSeconds);
    }

    protected static boolean isExpectedPageTitle(@NotNull String selector, @NotNull String title) {
        return isExpectedPageTitle(selector, title, WAIT_TIME_SECONDS);
    }

    protected static boolean isExpectedPageTitle(@NotNull String selector, @NotNull String title, int horizonSeconds) {
        boolean isCurrentPage = false;

        if(isElementPresentWithin(selector, horizonSeconds)){
            isCurrentPage = contains(selector, title);
        }

        return isCurrentPage;
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String title) {
        return isNotExpectedPageTitle(getMainTitleSelector(), title);
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String title, int horizonSeconds) {
        return isNotExpectedPageTitle(getMainTitleSelector(), title, horizonSeconds);
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String selector, @NotNull String title) {
        return isNotExpectedPageTitle(selector, title, WAIT_TIME_SECONDS);
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String selector, @NotNull String title, int horizonSeconds) {
        return !isExpectedPageTitle(selector, title, horizonSeconds);
    }

    public static void untilExpectedPageTitle(@NotNull String selector, @NotNull String pageTitle) throws IncorrectPageTitleException, UninitialisedDriverException {
        untilExpectedPageTitle(selector, pageTitle, WAIT_TIME_SECONDS);
    }

    public static void untilExpectedPageTitle(@NotNull String pageTitle) throws IncorrectPageTitleException, UninitialisedDriverException {
        untilExpectedPageTitle(pageTitle, WAIT_TIME_SECONDS);
    }

    public static void untilExpectedPageTitle(@NotNull String pageTitle, int horizonSeconds) throws IncorrectPageTitleException, UninitialisedDriverException {
        untilExpectedPageTitle(MAIN_TITLE_SELECTOR, pageTitle, horizonSeconds);
    }

    public static void untilExpectedPageTitle(@NotNull String selector, @NotNull String pageTitle, int horizonSeconds) throws IncorrectPageTitleException, UninitialisedDriverException {
        if(!isExpectedPageTitle(selector, pageTitle, horizonSeconds)){
            throw new IncorrectPageTitleException(String.format("[ERROR] The page title for the current page should be '%s' but was '%s'", pageTitle, Browser.getPageTitle()));
        }
    }

    public static void untilNotExpectedPageTitle(@NotNull String selector, @NotNull String pageTitle) throws IncorrectPageTitleException, UninitialisedDriverException {
        untilNotExpectedPageTitle(selector, pageTitle, WAIT_TIME_SECONDS);
    }

    public static void untilNotExpectedPageTitle(@NotNull String pageTitle) throws IncorrectPageTitleException, UninitialisedDriverException {
        untilNotExpectedPageTitle(pageTitle, WAIT_TIME_SECONDS);
    }

    public static void untilNotExpectedPageTitle(@NotNull String pageTitle, int horizonSeconds) throws IncorrectPageTitleException, UninitialisedDriverException {
        untilNotExpectedPageTitle(MAIN_TITLE_SELECTOR, pageTitle, horizonSeconds);
    }

    public static void untilNotExpectedPageTitle(@NotNull String selector, @NotNull String pageTitle, int horizonSeconds) throws IncorrectPageTitleException {
        if(!isNotExpectedPageTitle(selector, pageTitle, horizonSeconds)){
            throw new IncorrectPageTitleException(String.format("[ERROR] The page title for the page did not change after %d seconds", horizonSeconds));
        }
    }

    protected static boolean isExpectedTextInElementWithin(@NotNull String selector, @NotNull String expectedText, int seconds) {
        SelectorType selectorType = SelectorType.CSS;
        return isExpectedTextInElementWithin(selector, selectorType, expectedText, seconds);
    }

    protected static boolean isExpectedTextInElementWithin(@NotNull String selector, @NotNull SelectorType selectorType, @NotNull String expectedText, int seconds) {
        boolean found = true;

        try {
            untilExpectedTextInElement(selector, selectorType, expectedText, seconds);
        } catch (org.openqa.selenium.TimeoutException e) {
            found = false;
        }

        return found;
    }

    public static void untilExpectedTextInElement(@NotNull String selector, @NotNull String expectedText, int seconds) {
        SelectorType selectorType = SelectorType.CSS;
        untilExpectedTextInElement(selector, selectorType, expectedText, seconds);
    }

    public static void untilExpectedTextInElement(@NotNull String selector, @NotNull SelectorType selectorType, @NotNull String expectedText, int seconds) {
        (new WebDriverWait(getDriver(), seconds)).until(ExpectedConditions.textToBePresentInElementLocated(by(selector, selectorType), expectedText));
    }

    public static void untilNotExpectedTextInElement(@NotNull String selector, @NotNull String expectedText, int seconds) {
        SelectorType selectorType = SelectorType.CSS;
        untilNotExpectedTextInElement(selector, selectorType, expectedText, seconds);
    }

    public static void untilNotExpectedTextInElement(@NotNull String selector, @NotNull SelectorType selectorType, @NotNull String expectedText, int seconds) {
        (new WebDriverWait(getDriver(), seconds)).until(not(ExpectedConditions.textToBePresentInElementLocated(by(selector, selectorType), expectedText)));
    }

}
