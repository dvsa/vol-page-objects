package org.dvsa.testing.lib.pages.conditions;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public final class ElementCondition {

    public static ExpectedCondition<Boolean> isEnabled(@NotNull WebElement element) {
        return new ExpectedCondition<Boolean>(){
            @Override
            public Boolean apply(final WebDriver driver) {
                return element.isEnabled();
            }

            @Override
            public String toString() {
                return String.format("Enabled element: %s", element);
            }
        };
    }

}
