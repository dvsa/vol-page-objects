package org.dvsa.testing.lib.pages;

import org.dvsa.testing.lib.Browser;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    static void enterField(@NotNull String selector, @NotNull String text){
        find(selector).sendKeys(text);
    }

    static void click(@NotNull String selector){
       find(selector).click();
    }

    static WebDriver getDriver(){
        return Browser.getDriver();
    }

    static String createNameAttributeSelector(@NotNull String element, @NotNull String value){
        return createAttributeSelector(element, "name", value);
    }

    static String createAttributeSelector(@NotNull String element, @NotNull String attribute, @NotNull String value){
        return String.format("%s[%s=\"%s\"]", element, attribute, value);
    }

    private static WebElement find(@NotNull String selector){
        return Browser.getDriver().findElement(By.cssSelector(selector));
    }

    static boolean isElementPresent(@NotNull String selector){
        return getDriver().findElement(By.cssSelector(selector)) != null;
    }

    static boolean isNotSelected(@NotNull String selector){
        return !isSelected(selector);
    }

    static boolean isSelected(@NotNull String selector){
        return getDriver().findElement(By.cssSelector(selector)).isSelected();
    }

    static void select(@NotNull String selector){
        if(isNotSelected(selector)){
            click(selector);
        }
    }

    static void deselect(@NotNull String selector){
        if(isSelected(selector)){
            click(selector);
        }
    }

    static void isNotPresent(@NotNull String selector, int timeToWait){
        new WebDriverWait(getDriver(), timeToWait).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(selector)));
    }

    static void isNotInDOM(@NotNull String selector, int timeToWait){
        new WebDriverWait(getDriver(), timeToWait).until(BasePage.absenceOfElementLocated(By.cssSelector(selector)));
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

}
