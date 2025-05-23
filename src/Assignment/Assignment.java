package Assignment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Assignment {
	private String title;
	private String description;
	private LocalDate dueDate;

	// Constructor for the Assignment class with validation
	public Assignment(String title, String description, LocalDate dueDate) {
		// Validates the title and description
		// Throws IllegalArgumentException if any argument is invalid
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Error: Title cannot be empty.");
		}
		if (description == null || description.isEmpty()) {
			throw new IllegalArgumentException("Error: Description cannot be empty.");
		}

		// Validate that the due date is not in the past
		if (dueDate == null) {
			throw new IllegalArgumentException("Error: Due date cannot be null.");
		}
		if (dueDate.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Error: Due date cannot be in the past.");
		}

		// Assign values to the instance variables
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
	}

	// Getters for title, description, and due date

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	// Sets the due date
	public void setDueDate(LocalDate newDueDate) {
		if (newDueDate == null) {
			System.out.println("Error: Due date cannot be null.");
			return;
		}
		if (newDueDate.isBefore(LocalDate.now())) {
			System.out.println("Error: Due date cannot be in the past.");
			return;
		}
		this.dueDate = newDueDate;
	}

	// Prints a message if the assignment is overdue
	public void checkOverdue() {
		if (LocalDate.now().isAfter(dueDate)) {
			System.out.println("Assignment is Overdue");
		}

	}

	// Formats LocalDate using the DateTimeFormatter
	public String formattedDueDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
		return dueDate.format(formatter);
	}

	// Boolean method that returns true if all fields are non-null and due date is
	// not in the past
	public boolean isValid() {
		return title != null && description != null && dueDate != null && !dueDate.isBefore(LocalDate.now());
	}

	// Returns string representation of assignment with it's title, description, and
	// formatted due date
	public String toFileString() {
		return "Assignment {" +
				"Title='" + title + '\'' +
				", Description='" + description + '\'' +
				", Due Date='" + formattedDueDate() + '\'' +
				'}';
	}
}