package GradeSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                double marks = Double.parseDouble(model.getValueAt(i, 1).toString());
                double total = Double.parseDouble(model.getValueAt(i, 2).toString());
                if (total != 0) {
                    double percent = (marks / total) * 100.0;
                    model.setValueAt(String.format("%.1f", percent), i, 3);
                } else {
                    model.setValueAt("0", i, 3);
                }
            } catch (Exception e) {
                model.setValueAt("0", i, 3);
            }
        }
    }
}
