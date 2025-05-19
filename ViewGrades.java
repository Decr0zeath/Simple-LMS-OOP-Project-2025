import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
//import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;


public class ViewGrades {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("View Assignments");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());


        // Adding color to BG Color to student grades
        JPanel topPanel = new JPanel(new BorderLayout());
        Color greenColor = Color.decode("#458846");
        topPanel.setBackground(greenColor);

        
        JLabel title = new JLabel("Assignments");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setOpaque(true); // Show background color
        title.setBackground(greenColor);
        title.setForeground(Color.BLACK); // For Black text
        title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0)); // add spacing
        topPanel.add(title, BorderLayout.WEST);
        frame.add(topPanel, BorderLayout.NORTH);

        

        // Column names
        String[] columns = {
            "Course", "Assignment Title", "Grades Given"
        };

        // test data
        // Sample data for the table
        Object[][] data = {
            {"Prog 2", "Activity 1 - Arrays"," 50 / 70"},
            {"Prog 2","Activity 2 - Loops", " 60 / 70"},
            {"Prog 2", "Activity 3 - Functions", " 70 / 70"},
            {"Prog 2", "Activity 4 - Classes", " 80 / 100"},
            {"Prog 2", "Activity 5 - Inheritance", " 90 / 100"},
            {"Prog 2", "Activity 6 - Polymorphism", "100 / 100"},
            {"Prog 2", "Activity 7 - Interfaces", "110 / 150"},
            {"Prog 2", "Activity 8 - Abstract Classes", "120 / 150"},
            {"Prog 2", "Activity 9 - Exception Handling", "130 / 150"},
            {"Prog 2", "Activity 10 - File I/O", "140 / 150"},
            {"DVA", "Activity 1 - Arrays"," 50 / 70"},
            {"DVA", "Activity 2 - Loops", " 60 / 70"},
            {"DVA", "Activity 3 - Functions", " 70 / 70"},
            {"DVA", "Activity 4 - Classes", " 80 / 100"},
            {"DVA", "Activity 5 - Inheritance", " 90 / 100"},
            {"DVA", "Activity 6 - Polymorphism", "100 / 100"},
            {"DVA", "Activity 7 - Interfaces", "110 / 150"},
            {"DVA", "Activity 8 - Abstract Classes", "120 / 150"},
            {"DVA", "Activity 9 - Exception Handling", "130 / 150"},
            {"DVA", "Activity 10 - File I/O", "140 / 150"}
        };

        //Object[][] data = new Object[0][];

        // For the table
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        JTable table = new JTable(model);// Create table with model
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
        table.setRowHeight(35); // increase row height for better readability


        // Center cell data
        ((DefaultTableModel) table.getModel()).fireTableDataChanged();
        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


        // Lock column reordering
        table.getTableHeader().setReorderingAllowed(false);


        // Scroll pane with horizontal and vertical scroll
        JScrollPane scrollPane = new JScrollPane(table,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        // Add components to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

}
