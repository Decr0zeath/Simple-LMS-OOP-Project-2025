import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ViewGrades {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Learning Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());

        // Master data
        Object[][] masterData = {
            {"Prog 2", "Activity 1 - Arrays","50 / 70"},
            {"Prog 2","Activity 2 - Loops", "60 / 70"},
            {"Prog 2", "Activity 3 - Functions", "70 / 70"},
            {"Prog 2", "Activity 4 - Classes", "80 / 100"},
            {"Prog 2", "Activity 5 - Inheritance", "90 / 100"},
            {"Prog 2", "Activity 6 - Polymorphism", "100 / 100"},
            {"Prog 2", "Activity 7 - Interfaces", "110 / 150"},
            {"Prog 2", "Activity 8 - Abstract Classes", "120 / 150"},
            {"Prog 2", "Activity 9 - Exception Handling", "130 / 150"},
            {"Prog 2", "Activity 10 - File I/O", "140 / 150"},
            {"DVA", "Activity 1 - Arrays","50 / 70"},
            {"DVA", "Activity 2 - Loops", "60 / 70"},
            {"DVA", "Activity 3 - Functions", "70 / 70"},
            {"DVA", "Activity 4 - Classes", "80 / 100"},
            {"DVA", "Activity 5 - Inheritance", "90 / 100"},
            {"DVA", "Activity 6 - Polymorphism", "100 / 100"},
            {"DVA", "Activity 7 - Interfaces", "110 / 150"},
            {"DVA", "Activity 8 - Abstract Classes", "120 / 150"},
            {"DVA", "Activity 9 - Exception Handling", "130 / 150"},
            {"DVA", "Activity 10 - File I/O", "140 / 150"},
            {"QM", "Activity 1 - Introduction", "60 / 100"},
            {"QM", "Activity 2 - Probability", "70 / 100"},
            {"QM", "Activity 3 - Statistics", "90 / 100"}
        };

        String[] columns = {"Course", "Assignment Title", "Grades Given"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(35);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Top panel with dropdown and label
        JPanel topPanel = new JPanel(new BorderLayout());
        Color greenColor = Color.decode("#458846");
        topPanel.setBackground(greenColor);

        String[] courses = {"All", "Prog 2", "DVA", "QM"};
        JComboBox<String> courseDropdown = new JComboBox<>(courses);
        courseDropdown.setFont(new Font("Raleway", Font.PLAIN, 16));
        courseDropdown.setBackground(greenColor);
        courseDropdown.setForeground(Color.BLACK);
        courseDropdown.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(courseDropdown, BorderLayout.EAST);

        JLabel title = new JLabel("Assignments");
        title.setFont(new Font("Raleway", Font.BOLD, 20));
        title.setOpaque(true);
        title.setBackground(greenColor);
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
        topPanel.add(title, BorderLayout.WEST);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Load all data initially
        updateTable(model, masterData, "All");

        // Add action listener to filter based on selected course
        courseDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCourse = courseDropdown.getSelectedItem().toString();
                updateTable(model, masterData, selectedCourse);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Helper method to update table rows based on selected course
    public static void updateTable(DefaultTableModel model, Object[][] allData, String course) {
        model.setRowCount(0); // Clear existing rows
        for (Object[] row : allData) {
            if (course.equals("All") || row[0].equals(course)) {
                model.addRow(row);
            }
        }
    }
}
