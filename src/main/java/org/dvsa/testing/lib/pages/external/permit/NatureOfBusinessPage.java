package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.permit.enums.GoodType;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class NatureOfBusinessPage extends BasePage {
    private static String TYPE_OF_GOOD_TEMPLATE = "//label[contains(text(), '%s')]/input";

    private static String SAVE_AND_CONTINUE_BUTTON = "input[type='submit']";
    private static String CANCEL_BUTTON = "//a[contains(text(), 'Cancel')]";

    public static void typeOfGoods(@NotNull GoodType... goods) {
        Stream.of(goods).forEach((good) -> {
            String selector = String.format(TYPE_OF_GOOD_TEMPLATE, good.toString());

            scrollAndClick(
                    String.format(TYPE_OF_GOOD_TEMPLATE, good.toString()),
                    SelectorType.XPATH
            );
        });
    }

    public static void saveAndContinue() {
        scrollAndClick(SAVE_AND_CONTINUE_BUTTON);
    }

    public static void cancel() {
        scrollAndClick(CANCEL_BUTTON, SelectorType.XPATH);
    }

}
