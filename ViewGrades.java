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
        JFrame frame = new JFrame("View Grades");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setLayout(new BorderLayout());

        // Adding color to BG Color to student grades
        JPanel topPanel = new JPanel(new BorderLayout());
        Color greenColor = Color.decode("#458846");
        topPanel.setBackground(greenColor);


        JLabel title = new JLabel("Student Grades", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setOpaque(true); // Needed to show background color
        title.setBackground(greenColor);
        title.setForeground(Color.BLACK);// For Black text
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));// add spacing
        topPanel.add(title, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);

        

        // Column names
        String[] columns = {
            "Course", "Assignment Title", "Grades Given"
        };

        // Add data
        Object[][] data = {
            {"Prog 2", "Activity 1 - Vending Machine",}
        };

        // No data add later for prac
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
        table.setRowHeight(30); // Optional: increase row height for better readability

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

        frame.setVisible(true);
    }

}
