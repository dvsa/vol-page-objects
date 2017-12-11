package org.dvsa.testing.lib.pages;

import org.dvsa.testing.lib.Browser;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    public static void enterField(@NotNull String selector, @NotNull String text){
        find(selector).sendKeys(text);
    }

    public static void click(@NotNull String selector){
       find(selector).click();
    }

    protected static WebDriver getDriver(){
        return Browser.getDriver();
    }

    protected static String createNameAttributeSelector(@NotNull String element, @NotNull String value){
        return createAttributeSelector(element, "name", value);
    }

    protected static String createAttributeSelector(@NotNull String element, @NotNull String attribute, @NotNull String value){
        return String.format("%s[%s=\"%s\"]", element, attribute, value);
    }

    private static WebElement find(@NotNull String selector){
        return Browser.getDriver().findElement(By.cssSelector(selector));
    }

    protected static boolean isElementPresent(@NotNull String selector){
        return getDriver().findElement(By.cssSelector(selector)) != null;
    }

    protected static boolean isNotSelected(@NotNull String selector){
        return !isSelected(selector);
    }

    protected static boolean isSelected(@NotNull String selector){
        return getDriver().findElement(By.cssSelector(selector)).isSelected();
    }

    public static void select(@NotNull String selector){
        if(isNotSelected(selector)){
            click(selector);
        }
    }

    public static void deselect(@NotNull String selector){
        if(isSelected(selector)){
            click(selector);
        }
    }

}
