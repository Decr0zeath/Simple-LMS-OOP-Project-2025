package GradeAssign;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JPanel {
    private TablePanel tablePanel;
    private TotalLabelPanel totalLabelPanel;
    private ButtonPanel buttonPanel;
    private StudentGradeManager gradeManager;

    public MainFrame() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(0,128,0));

        tablePanel = new TablePanel();
        totalLabelPanel = new TotalLabelPanel();
        gradeManager = new StudentGradeManager();

        // Add sample students with default data
        gradeManager.addStudent(null, getDefaultData());
        gradeManager.addStudent(null, getDefaultData());
        gradeManager.addStudent(null, getDefaultData());

        buttonPanel = new ButtonPanel(tablePanel, totalLabelPanel, gradeManager);

        add(buttonPanel.getPanel(), BorderLayout.NORTH);
        add(tablePanel.getScrollPane(), BorderLayout.CENTER);
        add(totalLabelPanel.getLabel(), BorderLayout.SOUTH);
    }

    private Object[][] getDefaultData() {
        return new Object[][] {
            {null, 0, null, "0"},
            {null, 0, null, "0"},
            {null, 0, null, "0"},
            {null, 0, null, "0"}
        };
    }
}
