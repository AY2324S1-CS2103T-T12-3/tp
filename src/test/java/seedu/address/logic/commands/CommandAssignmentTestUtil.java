package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_PLANNEDFINISHDATE;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.IsoDate;

/**
 * Contains helper methods for testing commands.
 */
public class CommandAssignmentTestUtil {

    public static final String VALID_NAME_ASSIGNMENT = "CS2100 Assignment 2";
    public static final String VALID_NAME_PROJECT = "CS2103T Quiz";
    public static final String VALID_DESCRIPTION_ASSIGNMENT = "Assignment until K-maps";
    public static final String VALID_DESCRIPTION_PROJECT = "Lecture Quiz";
    public static final Boolean VALID_STATUS_DONE = false;
    public static final Boolean VALID_STATUS_UNDONE = false;
    public static final LocalDateTime VALID_DEADLINE_ASSIGNMENT =
            LocalDateTime.parse("2023-11-31 18:45", DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT));
    public static final LocalDateTime VALID_PLANNED_DATE =
            LocalDateTime.parse("2023-12-02 18:45", DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT));;
    public static final String VALID_TAG_ASSIGNMENT = "assignment";
    public static final String VALID_TAG_PROJECT = "CS2103T";

    public static final String NAME_DESC_ASSIGNMENT = " " + PREFIX_NAME + VALID_NAME_ASSIGNMENT;
    public static final String NAME_DESC_PROJECT = " " + PREFIX_NAME + VALID_NAME_PROJECT;
    public static final String DESCRIPTION_DESC_ASSIGNMENT = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_ASSIGNMENT;
    public static final String DESCRIPTION_DESC_PROJECT = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_PROJECT;
    public static final String STATUS_DESC_ASSIGNMENT = " " + PREFIX_STATUS + VALID_STATUS_DONE;
    public static final String STATUS_DESC_PROJECT = " " + PREFIX_STATUS + VALID_STATUS_UNDONE;
    public static final String DEADLINE_DESC = " " + PREFIX_ENDDATE + VALID_DEADLINE_ASSIGNMENT;
    public static final String PLANNED_DATE_DESC = " " + PREFIX_PLANNEDFINISHDATE + VALID_PLANNED_DATE;
    public static final String TAG_DESC_ASSIGNMENT = " " + PREFIX_TAG + VALID_TAG_ASSIGNMENT;
    public static final String TAG_DESC_PROJECT = " " + PREFIX_TAG + VALID_TAG_PROJECT;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Assignment&"; // '&' not allowed in names
    public static final String INVALID_DESC_DESC = " " + PREFIX_DESCRIPTION + "!!!"; // '!' not allowed in description
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "yay"; // wrong format for status
    public static final String INVALID_ENDDATE_DESC = " " + PREFIX_ENDDATE + "11-22-2021"; // wrong format
    public static final String INVALID_PLANNED_DESC = " " + PREFIX_PLANNEDFINISHDATE + "11-22-2021"; // wrong format
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "CS2103T*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Assignment> expectedFilteredList = new ArrayList<>(actualModel.getFilteredAssignmentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the assignment at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showAssignmentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAssignmentList().size());

        Assignment assignment = model.getFilteredAssignmentList().get(targetIndex.getZeroBased());
        final String name = assignment.getName().toString();

        assertEquals(1, model.getFilteredPersonList().size());
    }
}
