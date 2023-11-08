package seedu.address.logic.commands.internships.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.*;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandAssignmentTestUtil;
import seedu.address.logic.commands.internship.role.DeleteInternshipRoleCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;
import seedu.address.testutil.TypicalInternshipRoles;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteInternshipRoleCommand}.
 */

public class DeleteInternshipRoleCommandTest {

    private Model model_internshipRoleOnly =
            new ModelManager(TypicalInternshipRoles.getTypicalAddressBook_internshipRoleOnly(), new UserPrefs());

    private Model model_internshipRoleAndTask =
            new ModelManager(TypicalInternshipRoles.getTypicalAddressBook_internshipRoleAndTask(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        InternshipRole internshipRoleToDelete =
                model_internshipRoleOnly.getFilteredInternshipRoleList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        DeleteInternshipRoleCommand deleteCommand = new DeleteInternshipRoleCommand(INDEX_FIRST_IN_LIST);

        String expectedMessage = String.format(DeleteInternshipRoleCommand.MESSAGE_DELETE_INTERNSHIP_ROLE_SUCCESS,
                Messages.format(internshipRoleToDelete));

        ModelManager expectedModel = new ModelManager(model_internshipRoleOnly.getAddressBook(), new UserPrefs());
        expectedModel.deleteInternshipRole(internshipRoleToDelete);
        assertCommandSuccess(deleteCommand, model_internshipRoleOnly, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        InternshipRole internshipRoleToDelete =
                model_internshipRoleOnly.getFilteredInternshipRoleList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        DeleteInternshipRoleCommand deleteCommand = new DeleteInternshipRoleCommand(INDEX_FIRST_IN_LIST);

        String expectedMessage = String.format(DeleteInternshipRoleCommand.MESSAGE_DELETE_INTERNSHIP_ROLE_SUCCESS,
                Messages.format(internshipRoleToDelete));

        Model expectedModel = new ModelManager(model_internshipRoleOnly.getAddressBook(), new UserPrefs());
        expectedModel.deleteInternshipRole(internshipRoleToDelete);
        showNoInternshipRole(expectedModel);

        assertCommandSuccess(deleteCommand, model_internshipRoleOnly, expectedMessage, expectedModel);
    }

    @Test
    public void execute_associatedOneTaskDeleted_success() {
        InternshipRole internshipRoleToDelete =
                model_internshipRoleAndTask.getFilteredInternshipRoleList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        InternshipTask internshipTaskToDelete =
                model_internshipRoleAndTask.getFilteredInternshipTaskList().get(INDEX_FIRST_IN_LIST.getZeroBased());
        DeleteInternshipRoleCommand deleteCommand = new DeleteInternshipRoleCommand(INDEX_FIRST_IN_LIST);

        String expectedMessage = String.format(DeleteInternshipRoleCommand.MESSAGE_DELETE_INTERNSHIP_ROLE_SUCCESS,
                Messages.format(internshipRoleToDelete));

        Model expectedModel = new ModelManager(model_internshipRoleAndTask.getAddressBook(), new UserPrefs());
        expectedModel.deleteInternshipRole(internshipRoleToDelete);
        expectedModel.deleteInternshipTask(internshipTaskToDelete);

        assertCommandSuccess(deleteCommand, model_internshipRoleAndTask, expectedMessage, expectedModel);
    }

    @Test
    public void execute_associatedTwoTasksDeleted_success() {
        InternshipRole internshipRoleToDelete =
                model_internshipRoleAndTask.getFilteredInternshipRoleList().get(INDEX_SECOND_IN_LIST.getZeroBased());
        InternshipTask internshipTaskToDelete1 =
                model_internshipRoleAndTask.getFilteredInternshipTaskList().get(INDEX_SECOND_IN_LIST.getZeroBased());
        InternshipTask internshipTaskToDelete2 =
                model_internshipRoleAndTask.getFilteredInternshipTaskList().get(INDEX_THIRD_IN_LIST.getZeroBased());
        DeleteInternshipRoleCommand deleteCommand = new DeleteInternshipRoleCommand(INDEX_SECOND_IN_LIST);

        String expectedMessage = String.format(DeleteInternshipRoleCommand.MESSAGE_DELETE_INTERNSHIP_ROLE_SUCCESS,
                Messages.format(internshipRoleToDelete));

        Model expectedModel = new ModelManager(model_internshipRoleAndTask.getAddressBook(), new UserPrefs());
        expectedModel.deleteInternshipRole(internshipRoleToDelete);
        expectedModel.deleteInternshipTask(internshipTaskToDelete1);
        expectedModel.deleteInternshipTask(internshipTaskToDelete2);

        assertCommandSuccess(deleteCommand, model_internshipRoleAndTask, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model_internshipRoleOnly.getFilteredInternshipRoleList().size() + 1);
        DeleteInternshipRoleCommand deleteCommand = new DeleteInternshipRoleCommand(outOfBoundIndex);

        CommandAssignmentTestUtil.assertCommandFailure(deleteCommand, model_internshipRoleOnly,
                Messages.MESSAGE_INVALID_INTERNSHIP_ROLE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteInternshipRoleCommand deleteFirstCommand = new DeleteInternshipRoleCommand(INDEX_FIRST_IN_LIST);
        DeleteInternshipRoleCommand deleteSecondCommand = new DeleteInternshipRoleCommand(INDEX_SECOND_IN_LIST);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteInternshipRoleCommand deleteFirstCommandCopy = new DeleteInternshipRoleCommand(INDEX_FIRST_IN_LIST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteInternshipRoleCommand deleteCommand = new DeleteInternshipRoleCommand(targetIndex);
        String expected = DeleteInternshipRoleCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no internship roles.
     */
    private void showNoInternshipRole(Model model) {
        model.updateFilteredInternshipRoleList(p -> false);
        assertTrue(model.getFilteredInternshipRoleList().isEmpty());
    }
}
