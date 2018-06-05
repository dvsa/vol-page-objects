package org.dvsa.testing.lib.pages.external.permit;

import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.external.permit.enums.GoodType;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class NatureOfBusinessPage extends BasePage {
    // TODO: fill in identifiers once devs complete pages
    private static String TYPE_OF_GOOD_TEMPLATE;

    private static String SAVE_AND_CONTINUE_BUTTON;
    private static String CANCEL_BUTTON;

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
        click(SAVE_AND_CONTINUE_BUTTON);
    }

    public static void cancel() {
        click(CANCEL_BUTTON);
    }

}
