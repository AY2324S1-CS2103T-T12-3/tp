package seedu.address.logic.parser.assignment;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_PLANNEDFINISHDATE;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.person.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.assignment.AddAssignmentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.person.Prefix;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Date;
import seedu.address.model.assignment.Description;
import seedu.address.model.assignment.Name;
import seedu.address.model.assignment.NoDate;
import seedu.address.model.assignment.Status;
import seedu.address.model.tag.Tag;

/**
 * Parses an input that starts with add-a
 */
public class AddAssignmentParser implements Parser<AddAssignmentCommand> {

    @Override
    public AddAssignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION,
                PREFIX_STATUS, PREFIX_ENDDATE, PREFIX_PLANNEDFINISHDATE, PREFIX_TAG);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_NAME, PREFIX_STATUS, PREFIX_ENDDATE)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE));
        }

        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_STATUS,
                PREFIX_ENDDATE, PREFIX_PLANNEDFINISHDATE);

        Name name = ParserUtil.parseName(argumentMultimap
                .getValue(seedu.address.logic.parser.person.CliSyntax.PREFIX_NAME).get());

        Description description = ParserUtil.parseDescription(argumentMultimap.getValue(PREFIX_DESCRIPTION)
                .orElseGet(() -> ""));

        Status status = ParserUtil.parseStatus(argumentMultimap.getValue(PREFIX_STATUS).get());

        Date endDate = ParserUtil.parseDate(argumentMultimap.getValue(PREFIX_ENDDATE).get());

        Date plannedFinishDate = argumentMultimap.getValue(PREFIX_PLANNEDFINISHDATE).isEmpty()
                ? new NoDate()
                : ParserUtil.parseDate(argumentMultimap.getValue(PREFIX_PLANNEDFINISHDATE).get());

        Set<Tag> tagList = seedu.address.logic.parser.person.ParserUtil
                .parseTags(argumentMultimap.getAllValues(PREFIX_TAG));

        Assignment assignment = new Assignment(name, endDate, status, description, plannedFinishDate, tagList);

        return new AddAssignmentCommand(assignment);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
