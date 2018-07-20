package org.dvsa.testing.lib.pages.internal.dataretention;

import activesupport.string.Str;
import activesupport.system.out.Output;
import activesupport.MissingDriverException;
import org.dvsa.testing.lib.pages.BasePage;
import org.dvsa.testing.lib.pages.enums.dataretention.DataRetentionAction;
import org.dvsa.testing.lib.pages.enums.SelectorType;
import org.dvsa.testing.lib.pages.exception.InsufficientExistingDataException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordsPage extends BasePage {
    // Selectors
    private static String ACTION_BUTTON_TEMPLATE = ".actions-container button:nth-of-type(%d)";
    private static String MARK_FOR_REVIEW_BUTTON = String.format(ACTION_BUTTON_TEMPLATE, 3);
    private static String MARK_FOR_DELETE_BUTTON = String.format(ACTION_BUTTON_TEMPLATE, 4);
    private static String RECORD_ROWS = "form tbody tr";
    private static String ENTITY_TEMPLATE = "//*/tbody/tr[%d]/descendant::a[last()]";
    private static String RECORD_ROW_CHECKBOX = "form tbody tr:nth-of-type(%s) input[type='checkbox']";
    private static String NEXT_BUTTON = "//*[@class='pagination__item ']/a[text()[contains(.,'Next')]]";
    private static String TICK_ALL = "thead th:nth-of-type(5) input";
    private static String EMPTY_TABLE = "//*/p[text()[contains(.,'The table is empty')]]";
    private static String NUMBER_OF_RECORDS_TEXT = ".table__header h3";

    // Attributes
    private static String TABLE_HEADER_SELECTOR = "h3";
    // The title for this is the name of the data retention rule selected that these records are for.
    private static String TABLE_HEADER = "Data Retention Records";

    public static class DREntity {
        private String entityName;
        private String entityPK;

        public DREntity(@NotNull String entityName, @NotNull String entityPK) {
            this.entityName = entityName;
            this.entityPK = entityPK;
        }

        public String getEntityName() {
            return entityName;
        }

        public String getEntityPK() {
            return entityPK;
        }

    }

    // Behaviour
    /**
     * This selects the specified amount of records. Note that if the amount of records being selected
     * is greater than the amount of records on a single page, the records will be marked for the specified
     * action before further records on the following pages are selected due to selections not being saved
     * as you navigate the pagination, which is why they are marked before subsequent pages are loaded so that they're
     * states are saved.
     * @param dataRetentionAction This is the action the selected records are to be marked for, this can be
     *                           mark for delete or mark for review.
     * @param numRecordsToSelect This is the number of records to be selected.
     * @throws InsufficientExistingDataException Is thrown in the event that the number of selects specified
     * for selection is greater than the amount of records present.
     * @throws MissingDriverException Is thrown if the driver has not been initialised or has been closed.
     */
    public static void select(@NotNull DataRetentionAction dataRetentionAction, int numRecordsToSelect) throws InsufficientExistingDataException, MissingDriverException {
        if (isEmpty()) {
            throw new InsufficientExistingDataException();
        }

        int totalNumberOfRecordsFound = getNumberOfRecords();

        if (totalNumberOfRecordsFound > numRecordsToSelect) {
            throw new InsufficientExistingDataException(
                    Output.printColoredLog(
                            String.format(
                                    "[ERROR] Tried selecting %d records out of %d",
                                    numRecordsToSelect,
                                    totalNumberOfRecordsFound
                            )
                    )
            );
        }

        int recordsLeftToSelect = numRecordsToSelect;
        do {
            int numOfRecordOnCurrentPage = size(RECORD_ROWS, SelectorType.CSS);
            if (numOfRecordOnCurrentPage <= numRecordsToSelect) {
                mark(dataRetentionAction);
                recordsLeftToSelect -= numOfRecordOnCurrentPage;
            } else {
                for(int nthElement = 1; nthElement <= numOfRecordOnCurrentPage; nthElement++) {
                    click(String.format(RECORD_ROW_CHECKBOX, nthElement));
                    recordsLeftToSelect--;
                }
            }
        } while (recordsLeftToSelect > 0);
    }

    private static int getNumberOfRecords() throws MissingDriverException {
        String numberOfRecordsText = getText(NUMBER_OF_RECORDS_TEXT, SelectorType.CSS);
        return Integer.valueOf(Str.find("\\d+", numberOfRecordsText));
    }

    public static void selectAll(@NotNull DataRetentionAction dataRetentionAction) throws MissingDriverException, InsufficientExistingDataException {
        if (isEmpty()) {
            throw new InsufficientExistingDataException();
        }

        do {
            tickAll();
            mark(dataRetentionAction);
            if (isElementPresent(NEXT_BUTTON, SelectorType.XPATH)) {
                click(NEXT_BUTTON, SelectorType.XPATH);
            }
        } while (isElementPresent(NEXT_BUTTON, SelectorType.XPATH));
    }

    public static void mark(@NotNull DataRetentionAction dataRetentionAction) throws MissingDriverException {
        switch (dataRetentionAction) {
            case MARK_AS_DELETE:
                click(MARK_FOR_DELETE_BUTTON);
            break;
            case MARK_FOR_REVIEW:
                click(MARK_FOR_REVIEW_BUTTON);
            break;
            default:
                throw new IllegalArgumentException(Output.printColoredLog("[ERROR] " + dataRetentionAction + "is not a supported enumeration value"));
        }
    }

    public static void tickAll() throws MissingDriverException {
        click(TICK_ALL);
    }

    public static boolean isEmpty() throws MissingDriverException {
        return isElementPresent(EMPTY_TABLE, SelectorType.XPATH);
    }

    public static boolean isNotEmpty() throws MissingDriverException {
        return !isEmpty();
    }

    public static List<DREntity> extractRecordIdentifiers() throws MissingDriverException {
        List<DREntity> DREntities = new ArrayList<>();
        int numRecords = size(RECORD_ROWS);

        for (int index = 1; index <= numRecords; index++)
            DREntities.add(extractRecordIdentifier(index));

        return DREntities;
    }

    public static DREntity extractRecordIdentifier(int index) throws MissingDriverException {
        String entityText = getText(String.format(ENTITY_TEMPLATE, index), SelectorType.XPATH);

        Pattern p = Pattern.compile("(?<entityName>\\w+) (?<entityPK>\\w+)");
        Matcher m = p.matcher(entityText);
        m.matches();


        return new DREntity(m.group("entityName"), m.group("entityPK"));
    }

    public static void untilOnPage() throws MissingDriverException {
        untilExpectedTextInElement(TABLE_HEADER_SELECTOR, TABLE_HEADER, 5);
    }

}
