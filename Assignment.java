import java.time.LocalDate;

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

	// Method to extend the due date
	public void extendDueDate(int days) {
		this.dueDate = this.dueDate.plusDays(days); // Extends due date with the given number of days
	}

	// Changes due date (resets it)
	public void dueSat(LocalDate newDueDate) {
		this.dueDate = newDueDate; // Sets the new due date
	}

	// Checks if the assignment is overdue
	public boolean isOverdue() {
		return LocalDate.now().isAfter(dueDate);
	}
}
