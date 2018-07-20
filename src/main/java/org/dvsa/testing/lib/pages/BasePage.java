package org.dvsa.testing.lib.pages;

import activesupport.driver.Browser;
import activesupport.system.out.Output;
import activesupport.url.URL;
import activesupport.MissingDriverException;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.exception.ElementDidNotAppearWithinSpecifiedTimeException;
import org.dvsa.testing.lib.pages.exception.ElementDidNotDisappearWithinSpecifiedTimeException;
import org.dvsa.testing.lib.pages.exception.IncorrectPageTitleException;
import org.jetbrains.annotations.NotNull;
import org.joda.time.LocalDate;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;

import com.google.common.base.Function;

public abstract class BasePage {
    Browser browser = new Browser();
    private static final int WAIT_TIME_SECONDS = 10;

    private static String URI;
    private static String SCHEME;
    private static String DOMAIN;

    protected static String MAIN_TITLE_SELECTOR = "h1";

    protected static String getScheme() throws MissingDriverException {
        initialiseURLSectionsOnFirstCall();
        return SCHEME;
    }

    protected static String getDomain() throws MissingDriverException {
        initialiseURLSectionsOnFirstCall();
        return DOMAIN;
    }

    private static void initialiseURLSectionsOnFirstCall() throws MissingDriverException {
        if (URI == null) {
            updateURLSections();
        }
    }

    private static void updateURLSections() throws MissingDriverException {
        if (Browser.isInitialised()) {
            URI = Browser.getURL();
            SCHEME = URL.extractScheme(URI);
            DOMAIN = URL.extractDomain(URI);
        }
    }

    protected static String getMainTitleSelector() {
        return MAIN_TITLE_SELECTOR;
    }

    protected static String getAttribute(@NotNull String selector, @NotNull String attribute) throws MissingDriverException {
        return getAttribute(selector, SelectorType.CSS, attribute);
    }

    protected static String getAttribute(@NotNull String selector, @NotNull SelectorType selectorType, @NotNull String attribute) throws MissingDriverException {
        return find(selector, selectorType).getAttribute(attribute);
    }

    /**
     * This returns any text content that an element possesses.
     *
     * @param selector     This should be a CSS or XPATH selector which is used to identify which elements text is to be retrieved.
     * @param selectorType This is the type of selector that the argument selector is.
     * @return The specified elements text contents.
     * @throws MissingDriverException
     */
    protected static String getText(@NotNull String selector, @NotNull SelectorType selectorType) throws MissingDriverException {
        return find(selector, selectorType).getText();
    }

    protected static boolean hasText(@NotNull String selector, @NotNull SelectorType selectorType, @NotNull String text) throws MissingDriverException {
        return getText(selector, selectorType).equals(text);
    }

    protected static boolean hasText(@NotNull String selector, @NotNull String text) throws MissingDriverException {
        SelectorType selectorType = SelectorType.CSS;
        return hasText(selector, selectorType, text);
    }

    protected static List<String> getListValues(@NotNull String listSelector) throws MissingDriverException {
        List<String> optionValues = new LinkedList<>();

        Select select = new Select(find(listSelector));
        for (WebElement option : select.getOptions()) {
            optionValues.add(option.getText());
        }

        return optionValues;
    }

    /**
     * Enters text in the specified text input/textarea field found using the specified selector.
     *
     * @param selector The text input field/textarea that's to have text entered in it.
     * @param text     The text to be entered.
     * @throws MissingDriverException
     */
    protected static void enterField(@NotNull String selector, @NotNull String text) throws MissingDriverException {
        enterField(selector, text, false);
    }

    /**
     * Enters text in the specified text input/textarea field found using the specified selector.
     *
     * @param selector The text input field/textarea that's to have text entered in it.
     * @param text     The text to be entered.
     * @param append   Specified weather the input field/textarea should be cleared before entering the text.
     * @throws MissingDriverException
     */
    protected static void enterField(@NotNull String selector, @NotNull String text, boolean append) throws MissingDriverException {
        WebElement element = find(selector);

        if (!append) {
            element.clear();
        }

        element.sendKeys(text);
    }

    protected static void clickByLinkText(@NotNull String selector) {
        getDriver().findElement(By.partialLinkText(selector)).click();
    }

    protected static void checkTextisPresent(@NotNull String selector){
     getDriver().findElement(By.partialLinkText(selector)).isDisplayed();
    }

    protected static String colourChecker(@NotNull String selector, @NotNull String cssValue) {
       return getDriver().findElement(By.cssSelector(selector)).getCssValue(cssValue);
    }

    protected static void clickByName(@NotNull String selector) {
        getDriver().findElement(By.id(selector)).click();
    }

    protected static void selectValueFromDropDown(@NotNull String selector, @NotNull SelectorType selectorType, @NotNull String listValue) {
        Select selectItem = new Select(getDriver().findElement(by(selector, selectorType)));
        selectItem.selectByVisibleText(listValue);
    }

    protected static void selectValueFromDropDownByIndex(@NotNull String selector, @NotNull SelectorType selectorType, @NotNull int listValue) {
        Select selectItem = new Select(getDriver().findElement(by(selector, selectorType)));
        selectItem.selectByIndex(listValue);
    }

    protected static boolean isLinkPresent(String locator, int duration) {
        boolean itsFound = true;
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), duration);
            wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.partialLinkText(locator))));
        } catch (Exception e) {
            return false;

        }
        return itsFound;
    }

    protected static boolean isTextPresent(String locator, int duration) {
        boolean itsFound = true;
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), duration);
            wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(
                    String.format("//*[contains(text(),'%s')]", locator)))));
        } catch (Exception e) {
            return false;

        }
        return itsFound;
    }

    /**
     * Clicks on the first element found using the passed in CSS selector.
     * Note: There is an overloaded version of this method that allows you to specify which type of selector
     * you'd like to use, this overloaded version supports both CSS and XPATH.
     *
     * @param selector This should be a css selector.
     * @throws MissingDriverException This is thrown in the event that the driver has not been initialised.
     */
    protected static void click(@NotNull String selector) throws MissingDriverException {
        click(selector, SelectorType.CSS);
    }

    /**
     * Clicks on the first element found using the passed in selector.
     *
     * @param selector     This is the selector, this should be either CSS or XPATH.
     * @param selectorType This specifies what type of selector the first argument, selector, is.
     * @throws MissingDriverException
     */
    protected static void click(@NotNull String selector, @NotNull SelectorType selectorType) throws MissingDriverException {
        find(selector, selectorType).click();
    }

    protected static void scrollTo(@NotNull String selector, @NotNull SelectorType selectorType) throws MissingDriverException {
        scrollTo(selector, selectorType, WAIT_TIME_SECONDS);
    }

    protected static void scrollTo(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) throws MissingDriverException {
        WebElement element = find(selector, selectorType);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        untilPresent(selector, selectorType, seconds); // This will wait the specified amount of time until the element is in view
    }

    private static WebDriver getDriver() throws MissingDriverException {
        return Browser.getDriver();
    }

    protected static String nameAttribute(@NotNull String element, @NotNull String value) {
        return attributeSelector(element, "name", value);
    }

    protected static String attributeSelector(@NotNull String element, @NotNull String attribute, @NotNull String value) {
        return String.format("%s[%s=\"%s\"]", element, attribute, value);
    }

    protected static void list(@NotNull String listSelector, int itemPosition) throws MissingDriverException {
        list(listSelector, itemPosition, SelectorType.CSS);
    }

    protected static void list(@NotNull String listSelector, int itemPosition, @NotNull SelectorType selectorType) throws MissingDriverException {
        (new Select(find(listSelector, selectorType))).selectByIndex(itemPosition);
    }

    protected static void list(@NotNull String listSelector, @NotNull String visibleText) throws MissingDriverException {
        list(listSelector, visibleText, SelectorType.CSS);
    }

    protected static void list(@NotNull String listSelector, @NotNull String visibleText, @NotNull SelectorType selectorType) throws MissingDriverException {
        (new Select(find(listSelector, selectorType))).selectByVisibleText(visibleText);
    }

    private static WebElement find(@NotNull String selector) throws MissingDriverException {
        return find(selector, SelectorType.CSS);
    }

    private static WebElement find(@NotNull String selector, @NotNull SelectorType selectorType) throws MissingDriverException {
        By bySelector = by(selector, selectorType);
        return Browser.getDriver().findElement(bySelector);
    }

    private static List<WebElement> findAll(@NotNull String selector, @NotNull SelectorType selectorType) throws MissingDriverException {
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
            case NAME:
                bySelector = By.name(selector);
                break;
            case ID:
                bySelector = By.id(selector);
                break;
            default:
                throw new IllegalArgumentException("Only CSS and XPATH selector types are allowed");
        }
        return bySelector;
    }

    protected static int size(@NotNull String selector, @NotNull SelectorType selectorType) throws MissingDriverException {
        return findAll(selector, selectorType).size();
    }

    protected static int size(@NotNull String selector) throws MissingDriverException {
        return size(selector, SelectorType.CSS);
    }

    protected static boolean FisElementNotPresent(@NotNull String selector) throws MissingDriverException {
        return isElementNotPresent(selector, SelectorType.CSS);
    }

    protected static boolean isElementNotPresent(@NotNull String selector, SelectorType selectorType) throws MissingDriverException {
        return !isElementPresent(selector, selectorType);
    }

    protected static boolean isElementPresent(@NotNull String selector) throws MissingDriverException {
        return isElementPresent(selector, SelectorType.CSS);
    }

    protected static boolean isElementPresent(@NotNull String selector, SelectorType selectorType) throws MissingDriverException {
        boolean isElementPresent = true;

        try {
            find(selector, selectorType);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            isElementPresent = false;
        }

        return isElementPresent;
    }

    public static void untilElementPresent(@NotNull String selector) throws MissingDriverException, ElementDidNotAppearWithinSpecifiedTimeException {
        untilElementPresentWithin(selector, WAIT_TIME_SECONDS);
    }

    public static void untilElementPresent(@NotNull String selector, @NotNull SelectorType selectorType) throws MissingDriverException, ElementDidNotAppearWithinSpecifiedTimeException {
        untilElementPresentWithin(selector, selectorType, WAIT_TIME_SECONDS);
    }

    public static void untilElementPresentWithin(@NotNull String selector, int seconds) throws MissingDriverException, ElementDidNotAppearWithinSpecifiedTimeException {
        untilElementPresentWithin(selector, SelectorType.CSS, seconds);
    }

    public static void untilElementPresentWithin(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) throws MissingDriverException, ElementDidNotAppearWithinSpecifiedTimeException {
        boolean elementFound = isElementPresentWithin(selector, selectorType, seconds);
        if (!elementFound) {
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

    public static void untilElementNotPresentWithin(@NotNull String selector, int seconds) throws ElementDidNotDisappearWithinSpecifiedTimeException, MissingDriverException {
        boolean elementFound = isElementNotPresentWithin(selector, seconds);
        if (elementFound) {
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

    protected static boolean isElementPresentWithin(@NotNull String selector, int seconds) throws MissingDriverException {
        return isElementPresentWithin(selector, SelectorType.CSS, seconds);
    }

    @Deprecated
    protected static boolean isElementPresentWithin(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) throws MissingDriverException {
        return isInDOM(selector, selectorType, seconds);
    }

    protected static boolean isElementNotPresentWithin(@NotNull String selector, int seconds) throws MissingDriverException {
        return !isElementPresentWithin(selector, seconds);
    }

    protected static boolean isNotSelected(@NotNull String selector) throws MissingDriverException {
        return !isSelected(selector);
    }

    protected static boolean isSelected(@NotNull String selector) throws MissingDriverException {
        return getDriver().findElement(By.cssSelector(selector)).isSelected();
    }

    protected static void select(@NotNull String selector) throws MissingDriverException {
        if (isNotSelected(selector)) {
            click(selector);
        }
    }

    protected static void deselect(@NotNull String selector) throws MissingDriverException {
        if (isSelected(selector)) {
            click(selector);
        }
    }

    protected static boolean isNotPresent(@NotNull String selector, int seconds) throws MissingDriverException {
        return isNotPresent(selector, SelectorType.CSS, seconds);
    }

    protected static boolean isNotPresent(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) throws MissingDriverException {
        boolean isNotPresent = true;

        try {
            untilNotPresent(selector, selectorType, seconds);
        } catch (org.openqa.selenium.TimeoutException e) {
            isNotPresent = false;
        }

        return isNotPresent;
    }

    public static void untilNotPresent(@NotNull String selector, int seconds) throws MissingDriverException {
        untilNotPresent(selector, SelectorType.CSS, seconds);
    }

    public static void untilNotPresent(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) throws MissingDriverException {
        new WebDriverWait(getDriver(), seconds).until(not(ExpectedConditions.visibilityOfAllElementsLocatedBy(by(selector, selectorType))));
    }

    protected static boolean isPresent(@NotNull String selector, int seconds) throws MissingDriverException {
        return isPresent(selector, SelectorType.CSS, seconds);
    }

    protected static boolean isPresent(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) throws MissingDriverException {
        boolean isPresent = true;

        try {
            untilPresent(selector, selectorType, seconds);
        } catch (org.openqa.selenium.TimeoutException e) {
            isPresent = false;
        }

        return isPresent;
    }

    public static void untilPresent(@NotNull String selector, int seconds) throws MissingDriverException {
        untilPresent(selector, SelectorType.CSS, seconds);
    }

    public static void untilPresent(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) throws MissingDriverException {
        By by = by(selector, selectorType);
        new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    protected static boolean isNotInDOM(@NotNull String selector, int seconds) throws MissingDriverException {
        return isNotInDOM(selector, SelectorType.CSS, seconds);
    }

    protected static boolean isNotInDOM(@NotNull String selector, @NotNull SelectorType selectorType) throws MissingDriverException {
        return isInDOM(selector, selectorType, WAIT_TIME_SECONDS);
    }

    protected static boolean isNotInDOM(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) throws MissingDriverException {
        boolean isNotInDOM = true;

        try {
            untilInDOM(selector, selectorType, seconds);
        } catch (org.openqa.selenium.TimeoutException e) {
            isNotInDOM = false;
        }

        return isNotInDOM;
    }

    public static void untilNotInDOM(@NotNull String selector, int seconds) throws MissingDriverException {
        untilNotInDOM(selector, SelectorType.CSS, seconds);
    }

    public static void untilNotInDOM(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) throws MissingDriverException {
        new WebDriverWait(getDriver(), seconds).until(not(ExpectedConditions.presenceOfAllElementsLocatedBy(by(selector, selectorType))));
    }

    protected static boolean isInDOM(@NotNull String selector, int seconds) throws MissingDriverException {
        return isInDOM(selector, SelectorType.CSS, seconds);
    }

    protected static boolean isInDOM(@NotNull String selector, @NotNull SelectorType selectorType) throws MissingDriverException {
        return isInDOM(selector, selectorType, WAIT_TIME_SECONDS);
    }

    protected static boolean isInDOM(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) throws MissingDriverException {
        boolean isInDOM = true;

        try {
            untilInDOM(selector, selectorType, seconds);
        } catch (org.openqa.selenium.TimeoutException e) {
            isInDOM = false;
        }

        return isInDOM;
    }

    public static void untilInDOM(@NotNull String selector, int seconds) throws MissingDriverException {
        untilInDOM(selector, SelectorType.CSS, seconds);
    }

    public static void untilInDOM(@NotNull String selector, @NotNull SelectorType selectorType, int seconds) throws MissingDriverException {
        By by = by(selector, selectorType);
        new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected static boolean contains(@NotNull String selector, @NotNull String content) throws MissingDriverException {
        return find(selector).getText().contains(content);
    }

    protected static boolean isExpectedPageTitle(@NotNull String title) throws MissingDriverException {
        return isExpectedPageTitle(getMainTitleSelector(), title);
    }

    protected static boolean isExpectedPageTitle(@NotNull String title, int horizonSeconds) throws MissingDriverException {
        return isExpectedPageTitle(getMainTitleSelector(), title, horizonSeconds);
    }

    protected static boolean isExpectedPageTitle(@NotNull String selector, @NotNull String title) throws MissingDriverException {
        return isExpectedPageTitle(selector, title, WAIT_TIME_SECONDS);
    }

    protected static boolean isExpectedPageTitle(@NotNull String selector, @NotNull String title, int horizonSeconds) throws MissingDriverException {
        boolean isCurrentPage = false;

        if (isElementPresentWithin(selector, horizonSeconds)) {
            isCurrentPage = contains(selector, title);
        }

        return isCurrentPage;
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String title) throws MissingDriverException {
        return isNotExpectedPageTitle(getMainTitleSelector(), title);
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String title, int horizonSeconds) throws MissingDriverException {
        return isNotExpectedPageTitle(getMainTitleSelector(), title, horizonSeconds);
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String selector, @NotNull String title) throws MissingDriverException {
        return isNotExpectedPageTitle(selector, title, WAIT_TIME_SECONDS);
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String selector, @NotNull String title, int horizonSeconds) throws MissingDriverException {
        return !isExpectedPageTitle(selector, title, horizonSeconds);
    }

    public static void untilExpectedPageTitle(@NotNull String selector, @NotNull String pageTitle) throws IncorrectPageTitleException, MissingDriverException {
        untilExpectedPageTitle(selector, pageTitle, WAIT_TIME_SECONDS);
    }

    public static void untilExpectedPageTitle(@NotNull String pageTitle) throws IncorrectPageTitleException, MissingDriverException {
        untilExpectedPageTitle(pageTitle, WAIT_TIME_SECONDS);
    }

    public static void untilExpectedPageTitle(@NotNull String pageTitle, int horizonSeconds) throws IncorrectPageTitleException, MissingDriverException {
        untilExpectedPageTitle(MAIN_TITLE_SELECTOR, pageTitle, horizonSeconds);
    }

    public static void untilExpectedPageTitle(@NotNull String selector, @NotNull String pageTitle, int horizonSeconds) throws IncorrectPageTitleException, MissingDriverException {
        if (!isExpectedPageTitle(selector, pageTitle, horizonSeconds)) {
            throw new IncorrectPageTitleException(String.format("[ERROR] The page title for the current page should be '%s' but was '%s'", pageTitle, Browser.getPageTitle()));
        }
    }

    public static void untilNotExpectedPageTitle(@NotNull String selector, @NotNull String pageTitle) throws IncorrectPageTitleException, MissingDriverException {
        untilNotExpectedPageTitle(selector, pageTitle, WAIT_TIME_SECONDS);
    }

    public static void untilNotExpectedPageTitle(@NotNull String pageTitle) throws IncorrectPageTitleException, MissingDriverException {
        untilNotExpectedPageTitle(pageTitle, WAIT_TIME_SECONDS);
    }

    public static void untilNotExpectedPageTitle(@NotNull String pageTitle, int horizonSeconds) throws IncorrectPageTitleException, MissingDriverException {
        untilNotExpectedPageTitle(MAIN_TITLE_SELECTOR, pageTitle, horizonSeconds);
    }

    public static void untilNotExpectedPageTitle(@NotNull String selector, @NotNull String pageTitle, int horizonSeconds) throws MissingDriverException, IncorrectPageTitleException {
        if (!isNotExpectedPageTitle(selector, pageTitle, horizonSeconds)) {
            throw new IncorrectPageTitleException(String.format("[ERROR] The page title for the page did not change after %d seconds", horizonSeconds));
        }
    }

    protected static boolean isExpectedTextInElementWithin(@NotNull String selector, @NotNull String expectedText, int seconds) throws MissingDriverException {
        SelectorType selectorType = SelectorType.CSS;
        return isExpectedTextInElementWithin(selector, selectorType, expectedText, seconds);
    }

    protected static boolean isExpectedTextInElementWithin(@NotNull String selector, @NotNull SelectorType selectorType, @NotNull String expectedText, int seconds) throws MissingDriverException {
        boolean found = true;

        try {
            untilExpectedTextInElement(selector, selectorType, expectedText, seconds);
        } catch (org.openqa.selenium.TimeoutException e) {
            found = false;
        }

        return found;
    }

    public static void untilExpectedTextInElement(@NotNull String selector, @NotNull String expectedText, int seconds) throws MissingDriverException {
        SelectorType selectorType = SelectorType.CSS;
        untilExpectedTextInElement(selector, selectorType, expectedText, seconds);
    }

    public static void untilExpectedTextInElement(@NotNull String selector, @NotNull SelectorType selectorType, @NotNull String expectedText, int seconds) throws MissingDriverException {
        (new WebDriverWait(getDriver(), seconds)).until(ExpectedConditions.textToBePresentInElementLocated(by(selector, selectorType), expectedText));
    }

    public static void untilNotExpectedTextInElement(@NotNull String selector, @NotNull String expectedText, int seconds) throws MissingDriverException {
        SelectorType selectorType = SelectorType.CSS;
        untilNotExpectedTextInElement(selector, selectorType, expectedText, seconds);
    }

    public static void untilNotExpectedTextInElement(@NotNull String selector, @NotNull SelectorType selectorType, @NotNull String expectedText, int seconds) throws MissingDriverException {
        (new WebDriverWait(getDriver(), seconds)).until(not(ExpectedConditions.textToBePresentInElementLocated(by(selector, selectorType), expectedText)));
    }

    public static String getElementValueByText(@NotNull String selector, @NotNull SelectorType selectorType) {
        return getDriver().findElement(by(selector, selectorType)).getText();
    }

    public static WebElement findElement(@NotNull String selector, @NotNull SelectorType selectorType, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(by(selector, selectorType)));

        return getDriver().findElement(by(selector, selectorType));
    }

    public static boolean waitUntilElementIsEnabled(@NotNull String selector, @NotNull SelectorType selectorType) {
        return getDriver().findElement(by(selector, selectorType)).isEnabled();
    }

    public static void waitAndSelectByIndex(@NotNull String textWait, @NotNull String selector, @NotNull SelectorType selectorType, @NotNull int listValue) {
        FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(120, SECONDS)
                .pollingEvery(2, SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement foundIt = wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(
                        String.format("//*[contains(text(),'%s')]", textWait)))));
                Select selectItem = new Select(driver.findElement(By.xpath(selector)));
                selectItem.selectByIndex(listValue);
                return foundIt;
            }
        });
    }

    public static void waitAndClick(@NotNull String selector, @NotNull SelectorType selectorType) {
        FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(120, SECONDS)
                .pollingEvery(2, SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by(selector, selectorType))));
                submit.click();
                return submit;
            }
        });
    }

    public static void waitForTextToBePresent(@NotNull String selector) {
        FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(120, SECONDS)
                .pollingEvery(2, SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                WebElement submit = wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(
                        String.format("//*[contains(text(),'%s')]", selector)))));
                return submit;
            }
        });
    }

    public static void waitAndEnterText(@NotNull String selector, @NotNull SelectorType selectorType, @NotNull String textValue) {
        final FluentWait<WebDriver> wait = (new FluentWait(getDriver())).withTimeout(120L, TimeUnit.SECONDS).pollingEvery(2L, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
        WebElement element = (WebElement)wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement sendText = wait.until(ExpectedConditions.elementToBeClickable(by(selector,selectorType)));
                sendText.sendKeys(textValue);
                return sendText;
            }
        });
    }

    public static void uploadFile(@NotNull String inputBoxSelector, @NotNull String file, String jScript, @NotNull SelectorType selectorType) {
        if (jScript != null) {
            // making the file input element visible
            javaScriptExecutor(jScript);
        }
        WebElement element = getDriver().findElement(by(inputBoxSelector, selectorType));
        element.sendKeys(file);
    }

    public static Object javaScriptExecutor(String jsScript) {
        return ((JavascriptExecutor) getDriver()).executeScript(jsScript);
    }

    public static void enterText(@NotNull String selector, @NotNull String textValue, @NotNull SelectorType selectorType) {
        getDriver().findElement(by(selector,selectorType)).sendKeys(textValue);
    }

    public static int getCurrentDayOfMonth() {
        return LocalDate.now().getDayOfMonth();
    }

    public static int getCurrentMonth() {
        return LocalDate.now().getMonthOfYear();
    }

    public static int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    public static int getFutureDayOfMonth(@NotNull int days) {
        return LocalDate.now().plusDays(days).getDayOfMonth();
    }

    public static int getFutureMonth(@NotNull int months) {
        return LocalDate.now().plusMonths(months).getMonthOfYear();
    }

    public static int getFutureYear(@NotNull int years) {
        return LocalDate.now().plusYears(years).getYear();
    }

    public static int getPastDayOfMonth(@NotNull int days) {
        return LocalDate.now().minusDays(days).getDayOfMonth();
    }

    public static int getPastMonth(@NotNull int months) {
        return LocalDate.now().minusMonths(months).getMonthOfYear();
    }

    public static int getPastYear(@NotNull int years) {
        return LocalDate.now().minusYears(years).getYear();
    }

    public static void selectServiceType(@NotNull String inputBoxSelector, @NotNull String listValueSelector, @NotNull SelectorType selectorType) {
        WebElement element = getDriver().findElement(by(inputBoxSelector, selectorType));
        new Actions(getDriver()).moveToElement(element).click().perform();
        WebElement dropDownValueByIndex = getDriver().findElement(by(inputBoxSelector, selectorType));
        new Actions(getDriver()).moveToElement(dropDownValueByIndex).click().perform();
    }

    public static int returnTableRows(@NotNull String selector, @NotNull SelectorType selectorType){
        return getDriver().findElements(by(selector,selectorType)).size();
    }
}