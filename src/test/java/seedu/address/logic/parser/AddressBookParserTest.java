package seedu.address.logic.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_VOLUNTEER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.commands.AddVolunteerCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteVolunteerCommand;
import seedu.address.logic.commands.EditVolunteerCommand;
import seedu.address.logic.commands.EditVolunteerCommand.EditVolunteerDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindVolunteerCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListVolunteerCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SelectVolunteerCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.volunteer.NameContainsKeywordsPredicate;
import seedu.address.model.volunteer.Volunteer;
import seedu.address.testutil.EditVolunteerDescriptorBuilder;
import seedu.address.testutil.VolunteerBuilder;
import seedu.address.testutil.VolunteerUtil;

public class AddressBookParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Volunteer volunteer = new VolunteerBuilder().build();
        AddVolunteerCommand command = (AddVolunteerCommand) parser.parseCommand(VolunteerUtil.getAddVolunteerCommand(volunteer));
        assertEquals(new AddVolunteerCommand(volunteer), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteVolunteerCommand command = (DeleteVolunteerCommand) parser.parseCommand(
                DeleteVolunteerCommand.COMMAND_WORD + " " + INDEX_FIRST_VOLUNTEER.getOneBased());
        assertEquals(new DeleteVolunteerCommand(INDEX_FIRST_VOLUNTEER), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Volunteer volunteer = new VolunteerBuilder().build();
        EditVolunteerDescriptor descriptor = new EditVolunteerDescriptorBuilder(volunteer).build();
        EditVolunteerCommand command = (EditVolunteerCommand) parser.parseCommand(EditVolunteerCommand.COMMAND_WORD + " "
                + INDEX_FIRST_VOLUNTEER.getOneBased() + " " + VolunteerUtil.getEditVolunteerDescriptorDetails(descriptor));
        assertEquals(new EditVolunteerCommand(INDEX_FIRST_VOLUNTEER, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindVolunteerCommand command = (FindVolunteerCommand) parser.parseCommand(
                FindVolunteerCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindVolunteerCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_history() throws Exception {
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD) instanceof HistoryCommand);
        assertTrue(parser.parseCommand(HistoryCommand.COMMAND_WORD + " 3") instanceof HistoryCommand);

        try {
            parser.parseCommand("histories");
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(MESSAGE_UNKNOWN_COMMAND, pe.getMessage());
        }
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListVolunteerCommand.COMMAND_WORD) instanceof ListVolunteerCommand);
        assertTrue(parser.parseCommand(ListVolunteerCommand.COMMAND_WORD + " 3") instanceof ListVolunteerCommand);
    }

    @Test
    public void parseCommand_select() throws Exception {
        SelectVolunteerCommand command = (SelectVolunteerCommand) parser.parseCommand(
                SelectVolunteerCommand.COMMAND_WORD + " " + INDEX_FIRST_VOLUNTEER.getOneBased());
        assertEquals(new SelectVolunteerCommand(INDEX_FIRST_VOLUNTEER), command);
    }

    @Test
    public void parseCommand_redoCommandWord_returnsRedoCommand() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand("redo 1") instanceof RedoCommand);
    }

    @Test
    public void parseCommand_undoCommandWord_returnsUndoCommand() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand("undo 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        parser.parseCommand("");
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() throws Exception {
        thrown.expect(ParseException.class);
        thrown.expectMessage(MESSAGE_UNKNOWN_COMMAND);
        parser.parseCommand("unknownCommand");
    }
}
