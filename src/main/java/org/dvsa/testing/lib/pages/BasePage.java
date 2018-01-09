package org.dvsa.testing.lib.pages;

import activesupport.url.URL;
import org.dvsa.testing.lib.browser.Browser;
import org.dvsa.testing.lib.browser.exceptions.UninitialisedDriverException;
import org.dvsa.testing.lib.pages.exception.IncorrectPageTitleException;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    protected static void enterField(@NotNull String selector, @NotNull String text) throws UninitialisedDriverException {
        find(selector).sendKeys(text);
    }

    protected static void click(@NotNull String selector) throws UninitialisedDriverException {
       find(selector).click();
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

    private static WebElement find(@NotNull String selector) throws UninitialisedDriverException {
        return Browser.getDriver().findElement(By.cssSelector(selector));
    }

    protected static boolean isElementPresent(@NotNull String selector) throws UninitialisedDriverException {
        return find(selector) != null;
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

}
