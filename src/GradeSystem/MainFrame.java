package GradeSystem;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame frame;
    private TablePanel tablePanel;
    private TotalLabelPanel totalLabelPanel;
    private ButtonPanel buttonPanel;

    public MainFrame() {
        frame = new JFrame("OSCS LMS");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10)); 
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(0x458846));

        tablePanel = new TablePanel();
        totalLabelPanel = new TotalLabelPanel();
        buttonPanel = new ButtonPanel(tablePanel, totalLabelPanel);

        mainPanel.add(buttonPanel.getPanel(), BorderLayout.NORTH);
        mainPanel.add(tablePanel.getScrollPane(), BorderLayout.CENTER);
        mainPanel.add(totalLabelPanel.getLabel(), BorderLayout.SOUTH);

        frame.add(mainPanel);
    }

    public void show() {
        frame.setVisible(true);
    }
}
