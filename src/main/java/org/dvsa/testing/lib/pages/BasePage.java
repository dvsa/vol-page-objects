package org.dvsa.testing.lib.pages;

import activesupport.url.URL;
import org.dvsa.testing.lib.Browser;
import org.dvsa.testing.lib.pages.exception.IncorrectPageTitleException;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public class BasePage {

    private static String URI = Browser.getURL();
    private static String SCHEME = URL.extractScheme(URI);
    private static String DOMAIN = URL.extractDomain(URI);

    private static String PAGE_TITLE_SELECTOR = "h1";

    protected static String getScheme() {
        return SCHEME;
    }

    protected static String getDomain() {
        return DOMAIN;
    }

    protected static String getPageTitleSelector() {
        return PAGE_TITLE_SELECTOR;
    }

    protected static void enterField(@NotNull String selector, @NotNull String text){
        find(selector).sendKeys(text);
    }

    protected static void click(@NotNull String selector){
       find(selector).click();
    }

    private static WebDriver getDriver(){
        return Browser.getDriver();
    }

    protected static String nameAttribute(@NotNull String element, @NotNull String value){
        return attributeSelector(element, "name", value);
    }

    protected static String attributeSelector(@NotNull String element, @NotNull String attribute, @NotNull String value){
        return String.format("%s[%s=\"%s\"]", element, attribute, value);
    }

    private static WebElement find(@NotNull String selector){
        return Browser.getDriver().findElement(By.cssSelector(selector));
    }

    protected static boolean isElementPresent(@NotNull String selector){
        return find(selector) != null;
    }

    protected static boolean isElementPresentWithin(@NotNull String selector, long milliseconds){
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

    protected static boolean isElementNotPresentWithin(@NotNull String selector, long milliseconds){
        return !isElementPresentWithin(selector, milliseconds);
    }

    protected static boolean isNotSelected(@NotNull String selector){
        return !isSelected(selector);
    }

    protected static boolean isSelected(@NotNull String selector){
        return getDriver().findElement(By.cssSelector(selector)).isSelected();
    }

    protected static void select(@NotNull String selector){
        if(isNotSelected(selector)){
            click(selector);
        }
    }

    protected static void deselect(@NotNull String selector){
        if(isSelected(selector)){
            click(selector);
        }
    }

    protected static void isNotPresent(@NotNull String selector, int timeToWait){
        new WebDriverWait(getDriver(), timeToWait).until(not(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(selector))));
    }

    protected static void isPresent(@NotNull String selector, int timeToWait){
        new WebDriverWait(getDriver(), timeToWait).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(selector)));
    }

    protected static void isNotInDOM(@NotNull String selector, int timeToWait){
        new WebDriverWait(getDriver(), timeToWait).until(not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(selector))));
    }

    protected static void isInDOM(@NotNull String selector, int timeToWait){
        new WebDriverWait(getDriver(), timeToWait).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(selector)));
    }

    protected static boolean contains(@NotNull String selector, @NotNull String content){
        return find(selector).getText().contains(content);
    }

    protected static boolean isExpectedPageTitle( @NotNull String title){
        return isExpectedPageTitle(getPageTitleSelector(), title);
    }

    protected static boolean isExpectedPageTitle(@NotNull String title, long horizonMilliseconds){
        return isExpectedPageTitle(getPageTitleSelector(), title, horizonMilliseconds);
    }

    protected static boolean isExpectedPageTitle(@NotNull String selector, @NotNull String title){
        long horizonMilliseconds = 3000;
        return isExpectedPageTitle(selector, title, horizonMilliseconds);
    }

    protected static boolean isExpectedPageTitle(@NotNull String selector, @NotNull String title, long horizonMilliseconds){
        boolean isCurrentPage = false;

        if(isElementPresentWithin(selector, horizonMilliseconds)){
            isCurrentPage = contains(selector, title);
        }

        return isCurrentPage;
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String title){
        return isNotExpectedPageTitle(getPageTitleSelector(), title);
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String title, long horizonMilliseconds){
        return isNotExpectedPageTitle(getPageTitleSelector(), title, horizonMilliseconds);
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String selector, @NotNull String title){
        long horizonMilliseconds = 3000;
        return isNotExpectedPageTitle(selector, title, horizonMilliseconds);
    }

    protected static boolean isNotExpectedPageTitle(@NotNull String selector, @NotNull String title, long horizonMilliseconds){
        return !isExpectedPageTitle(selector, title, horizonMilliseconds);
    }

    protected static void untilExpectedPageTitle(@NotNull String selector, @NotNull String pageTitle) throws IncorrectPageTitleException {
        long horizonMilliseconds = 3000;
        untilExpectedPageTitle(selector, pageTitle, horizonMilliseconds);
    }

    protected static void untilExpectedPageTitle(@NotNull String pageTitle) throws IncorrectPageTitleException {
        long horizonMilliseconds = 3000;
        untilExpectedPageTitle(pageTitle, horizonMilliseconds);
    }

    protected static void untilExpectedPageTitle(@NotNull String pageTitle, long horizonMilliseconds) throws IncorrectPageTitleException {
        untilExpectedPageTitle(PAGE_TITLE_SELECTOR, pageTitle, horizonMilliseconds);
    }

    protected static void untilExpectedPageTitle(@NotNull String selector, @NotNull String pageTitle, long horizonMilliseconds) throws IncorrectPageTitleException {
        if(!isExpectedPageTitle(selector, pageTitle, horizonMilliseconds)){
            throw new IncorrectPageTitleException(String.format("[ERROR] The page title for the current page should be %s but was %s", pageTitle, Browser.getPageTitle()));
        }
    }

}
