package org.dvsa.testing.lib.pages;

import org.dvsa.testing.lib.Browser;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected static String PAGE_TITLE = "h1";

    protected static void enterField(@NotNull String selector, @NotNull String text){
        find(selector).sendKeys(text);
    }

    protected static void click(@NotNull String selector){
       find(selector).click();
    }

    protected static WebDriver getDriver(){
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
        new WebDriverWait(getDriver(), timeToWait).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(selector)));
    }

    protected static void isPresent(@NotNull String selector, int timeToWait){
        new WebDriverWait(getDriver(), timeToWait).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
    }

    protected static void isNotInDOM(@NotNull String selector, int timeToWait){
        new WebDriverWait(getDriver(), timeToWait).until(BasePage.absenceOfElementLocated(By.cssSelector(selector)));
    }

    protected static void isInDOM(@NotNull String selector, int timeToWait){
        new WebDriverWait(getDriver(), timeToWait).until(BasePage.presenceOfElementLocated(By.cssSelector(selector)));
    }

    protected static boolean contains(@NotNull String selector, @NotNull String content){
        return find(selector).getText().contains(content);
    }

    protected static boolean isCurrentPage(@NotNull String selector, @NotNull String title){
        long horizonMilliseconds = 3000;
        return isCurrentPage(selector, title, horizonMilliseconds);
    }

    protected static boolean isCurrentPage(@NotNull String selector, @NotNull String title, long horizonMilliseconds){
        boolean isCurrentPage = false;

        if(isElementPresentWithin(selector, horizonMilliseconds)){
            isCurrentPage = contains(selector, title);
        }

        return isCurrentPage;
    }

    private static ExpectedCondition<Boolean> absenceOfElementLocated(
            final By locator) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    driver.findElement(locator);
                    return false;
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return true;
                }
            }

            @Override
            public String toString() {
                return "element to not being present: " + locator;
            }
        };
    }

    private static ExpectedCondition<Boolean> presenceOfElementLocated(
            final By locator) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    driver.findElement(locator);
                    return true;
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "element to being present: " + locator;
            }
        };
    }

}
