import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Assignment {
	private String title;
	private String description;
	private LocalDate dueDate;

	public Assignment(String title, String description, LocalDate dueDate) {
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
}
