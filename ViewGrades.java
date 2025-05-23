import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewGrades {

    public static void main(String[] args) {
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

        JScrollPane scrollPane = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel topPanel = new JPanel(new BorderLayout());
        Color greenColor = Color.decode("#458846");
        topPanel.setBackground(greenColor);

        // Need for extension
        String[] courseidStrings = {"All","Programming 1 - SCS101", "Web Development - SCS102", "Digital Visual Arts - SCS103", "Introduction to Cybersecurity - SCS104", "Data Structures and Algorithm - SCS105"};
        JComboBox<String> courseDropdown = new JComboBox<>(courseidStrings);
        courseDropdown.setFont(new Font("Raleway", Font.PLAIN, 16));
        courseDropdown.setBackground(Color.WHITE);              // dropdown background white
        courseDropdown.setForeground(Color.BLACK);              // text color black
        courseDropdown.setOpaque(false);               // allow background color to show
        courseDropdown.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // internal padding

        // Wrap dropdown in a transparent JPanel to preserve green background of parent panel
        JPanel dropdownContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        dropdownContainer.setBackground(greenColor);  // match top panel background
        dropdownContainer.add(courseDropdown);

        topPanel.add(dropdownContainer, BorderLayout.EAST);// Add dropdown to the right side of the top panel
        JLabel title = new JLabel("Assignments");
        title.setFont(new Font("Raleway", Font.BOLD, 20));
        title.setOpaque(true);
        title.setBackground(greenColor);
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(5, 20, 10, 0));
        topPanel.add(title, BorderLayout.WEST);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void updateTable(DefaultTableModel model, Object[][] allData, String course) {
        model.setRowCount(0); // Clear table
        for (Object[] row : allData) {
            if (row[0].equals(course)) {
                model.addRow(new Object[]{row[1], row[2]});
            }
        }
    }
}
