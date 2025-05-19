import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Assignment {
	private String title;
	private String description;
	private LocalDate dueDate;

	// Constructor for the Assignment class with validation
	public Assignment(String title, String description, LocalDate dueDate) {
		// Validate the title and description
		if (title == null || title.isEmpty()) {
			System.out.println("Error: Title cannot be empty.");
			return;
		}
		if (description == null || description.isEmpty()) {
			System.out.println("Error: Description cannot be empty.");
			return;
		}

		// Validate that the due date is not in the past
		if (dueDate == null) {
			System.out.println("Error: Due date cannot be null.");
			return;
		}
		if (dueDate.isBefore(LocalDate.now())) {
			System.out.println("Error: Due date cannot be in the past.");
			return;
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

	// Returns string representation of assignment with it's title, description, and
	// formatted due date
	@Override
	public String toFileString() {
		return "Assignment {" +
				"Title='" + title + '\'' +
				", Description='" + description + '\'' +
				", Due Date='" + formattedDueDate() + '\'' +
				'}';
	}
}
