package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Date;
import seedu.address.model.assignment.Description;
import seedu.address.model.assignment.IsoDate;
import seedu.address.model.assignment.Name;
import seedu.address.model.assignment.Status;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Assignment objects.
 */
public class AssignmentBuilder {

    public static final String DEFAULT_NAME = "CS2105 Assignment 0";
    public static final LocalDateTime DEFAULT_DATE =
            LocalDateTime.parse("2023-11-29 18:00", DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT));
    public static final String DEFAULT_DESCRIPTION = "Programming assignment";
    public static final Boolean DEFAULT_STATUS = false;

    private Name name;
    private Status status;
    private Date deadline;
    private Date plannedEndDate;
    private Description description;
    private Set<Tag> tags;

    /**
     * Creates a {@code AssignmentBuilder} with the default details.
     */
    public AssignmentBuilder() {
        name = new Name(DEFAULT_NAME);
        status = new Status(DEFAULT_STATUS);
        deadline = new IsoDate(DEFAULT_DATE);
        plannedEndDate = new IsoDate(DEFAULT_DATE);
        description = new Description(DEFAULT_DESCRIPTION);
        tags = new HashSet<>();
    }

    /**
     * Initializes the AssignmentBuilder with the data of {@code assignmentToCopy}.
     */
    public AssignmentBuilder(Assignment assignmentToCopy) {
        name = assignmentToCopy.getName();
        status = assignmentToCopy.getStatus();
        deadline = assignmentToCopy.getEnd();
        plannedEndDate = assignmentToCopy.getPlannedFinishDate();
        description = assignmentToCopy.getDescription();
        tags = new HashSet<>(assignmentToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withStatus(Boolean status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withDeadline(LocalDateTime deadline) {
        this.deadline = new IsoDate(deadline);
        return this;
    }

    /**
     * Sets the {@code PlannedDate} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withPlannedDate(LocalDateTime plannedDate) {
        this.plannedEndDate = new IsoDate(plannedDate);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public Assignment build() {
        return new Assignment(name, deadline, status, description, plannedEndDate, tags);
    }

}
