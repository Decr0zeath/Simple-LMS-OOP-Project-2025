package AssignmentFiles;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import GradeAssign.StudentGradeManager;


public class ViewGrades {

    public static void main(String[] args, StudentGradeManager gradeManager) {
        JFrame frame = new JFrame("Learning Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(940, 550);
        frame.setLayout(new BorderLayout());

        // Sample assignment data grouped by course
        /*Object[][] masterData = {
            {"Prog 2", "Activity 1 - Arrays", "50 / 70"},
            {"Prog 2", "Activity 2 - Loops", "60 / 70"},
            {"DVA", "Quiz 1", "40 / 50"},
            {"QM", "Final Exam", "85 / 100"}
        };*/

        String[] columns = {"Assignment Title", "Grades Given"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model); // Create table with model
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(35);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();// Center align all cells
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.getTableHeader().setReorderingAllowed(false);

        JPanel topPanel = new JPanel(new BorderLayout());
        Color greenColor = Color.decode("#458846");
        topPanel.setBackground(greenColor);

        // Get all courses dynamically from gradeManager
String[] allCourses = gradeManager.getAllStudents(); // course IDs (e.g., "SCS101", "SCS102", ...)

String[] courseDropdownItems = new String[allCourses.length + 1];
courseDropdownItems[0] = "All"; // Add "All" at the top
System.arraycopy(allCourses, 0, courseDropdownItems, 1, allCourses.length);

JComboBox<String> courseDropdown = new JComboBox<>(courseDropdownItems);
courseDropdown.setFont(new Font("Raleway", Font.PLAIN, 16));
courseDropdown.setBackground(Color.WHITE);
courseDropdown.setForeground(Color.BLACK);
courseDropdown.setOpaque(false);
courseDropdown.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

// Populate table based on course selection
courseDropdown.addActionListener(e -> {
    String selectedCourse = (String) courseDropdown.getSelectedItem();
    model.setRowCount(0); // Clear previous rows

    for (String course : allCourses) {
        if (selectedCourse.equals("All") || selectedCourse.equals(course)) {
            Object[][] grades = gradeManager.getGrades(course);
            if (grades != null) {
                for (Object[] row : grades) {
                    String title = row[0] != null ? row[0].toString() : "Untitled";
                    String mark = row[1] != null ? row[1].toString() : "0";
                    String total = row[2] != null ? row[2].toString() : "0";
                    model.addRow(new Object[]{title, mark + " / " + total});
                }
            }
        }
    }
}

);}
}
