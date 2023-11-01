package seedu.address.storage;

import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path DUPLICATE_ASSIGNMENT_FILE = TEST_DATA_FOLDER
            .resolve("duplicateAssignmentAddressBook.json");
    private static final Path INVALID_NAME_ASSGN_FILE = TEST_DATA_FOLDER.resolve("emptyAssignmentNameAddressBook.json");
    private static final Path INVALID_DATE_ASSGN_FILE = TEST_DATA_FOLDER
            .resolve("invalidAssignmentDateAddressBook.json");
    private static final Path INVALID_PLAN_DATE_ASSGN_FILE = TEST_DATA_FOLDER
            .resolve("invalidPlanAssignmentDateAddressBook.json");

    @Test
    public void toModelType_duplicateAssignments_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ASSIGNMENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_ASSIGNMENTS,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_emptyAssignmentName_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_NAME_ASSGN_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidAssignmentDate_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_DATE_ASSGN_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidAssignmentPlanDate_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PLAN_DATE_ASSGN_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
