package GradeAssign;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TablePanel {
    private JTable table;
    private DefaultTableModel model;

    private final String[] columns = {"Subject", "Marks", "Total", "Grade"};

    public TablePanel() {
        model = new DefaultTableModel(null, columns) {
            public boolean isCellEditable(int row, int column) {
                return column == 1; 
            }
        };

        table = new JTable(model);
        table.setFillsViewportHeight(true);
   
        table.setPreferredScrollableViewportSize(new Dimension(900, 300)); 
        table.setRowHeight(30);
    }

    public JScrollPane getScrollPane() {
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    public DefaultTableModel getModel() {
        return model;
    }
    public void updateGrades() {
        boolean errorShown = false; 

        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                Object markObj = model.getValueAt(i, 1);
                Object totalObj = model.getValueAt(i, 2);

                if (markObj == null || totalObj == null) {
                    model.setValueAt("0", i, 3);
                    continue;
                }

                double marks = Double.parseDouble(markObj.toString());
                double total = Double.parseDouble(totalObj.toString());

                if (total != 0) {
                    double percent = (marks / total) * 100.0;
                    model.setValueAt(String.format("%.1f", percent), i, 3);
                } else {
                    model.setValueAt("0", i, 3);
                }

            } catch (NumberFormatException e) {
                model.setValueAt("0", i, 3);
                if (!errorShown) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid input detected. Please enter only numeric values for Marks.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    errorShown = true;
                }
            }
        }
    }

}
