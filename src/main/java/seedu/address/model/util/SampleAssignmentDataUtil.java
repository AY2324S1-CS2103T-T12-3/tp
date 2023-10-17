package seedu.address.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Description;
import seedu.address.model.assignment.IsoDate;
import seedu.address.model.assignment.Name;
import seedu.address.model.assignment.Status;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleAssignmentDataUtil {

    public static Assignment[] getSampleAssignments() {
        return new Assignment[]{
            new Assignment(new Name("CS2101 presentation"),
                new IsoDate(LocalDateTime.parse("2023-12-01 18:00",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(false), new Description("Prepare presentation"),
                new IsoDate(LocalDateTime.parse("2023-12-01 18:00",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                getTagSet("CS2101")),
            new Assignment(new Name("CS2103T peer review"),
                new IsoDate(LocalDateTime.parse("2023-11-31 18:00",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(false), new Description("Do peer review"),
                new IsoDate(LocalDateTime.parse("2023-11-31 18:00",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                getTagSet("CS2103T")),
            new Assignment(new Name("CS2103T milestone 1.2"),
                new IsoDate(LocalDateTime.parse("2023-11-30 18:00",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                new Status(false), new Description("Prepare milstone"),
                new IsoDate(LocalDateTime.parse("2023-11-29 18:00",
                        DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))),
                getTagSet("CS2103T"))
        };
    }

    public static ReadOnlyAddressBook getSampleAssignmment() {
        AddressBook sampleAb = new AddressBook();
        for (Assignment sampleAssignment : getSampleAssignments()) {
            sampleAb.addAssignment(sampleAssignment);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
