package GradeSystem;

import javax.swing.*;
import java.awt.*;

public class TotalLabelPanel {
    private JLabel totalLabel;

    public TotalLabelPanel() {
        totalLabel = new JLabel("Total Marks: 0 / Total Possible Marks: 0");
        totalLabel.setHorizontalAlignment(SwingConstants.LEFT);
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setPreferredSize(new Dimension(1000, 40));
    }

    public JLabel getLabel() {
        return totalLabel;
    }

    public void updateLabel(double marks, double total) {
        totalLabel.setText("Total Marks: " + marks + " / Total Possible Marks: " + total);
    }
}
