package GradeSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ButtonPanel {
    private JPanel buttonPanel;
    private JComboBox<String> studentDropdown;

    private TablePanel tablePanel;
    private TotalLabelPanel totalLabelPanel;

    private Map<String, Object[][]> studentGrades;

    private String currentStudent;

    public ButtonPanel(TablePanel tablePanel, TotalLabelPanel totalLabelPanel) {
        this.tablePanel = tablePanel;
        this.totalLabelPanel = totalLabelPanel;

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        buttonPanel.setBackground(new Color(0x458846));

        String[] students = {"Alice", "Bob", "Charlie", "David"};
        studentDropdown = new JComboBox<>(students);
        studentDropdown.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(studentDropdown);

        studentGrades = new HashMap<>();
        for (String student : students) {
            Object[][] data = {
                    {"OOP Assignment1", 0, 100, "0"},
                    {"OOP Assignment2", 0, 100, "0"},
                    {"OOP Assignment3", 0, 100, "0"},
                    {"OOP Assignment4", 0, 100, "0"}
            };
            studentGrades.put(student, data);
        }

        currentStudent = (String) studentDropdown.getSelectedItem();
        loadStudentData(currentStudent);

        studentDropdown.addActionListener(e -> {
            saveCurrentStudentData(currentStudent);
            currentStudent = (String) studentDropdown.getSelectedItem();
            loadStudentData(currentStudent);

            updateTotals();
        });

        tablePanel.getModel().addTableModelListener(e -> {
            if (e.getType() == javax.swing.event.TableModelEvent.UPDATE && e.getColumn() == 1) {
                tablePanel.updateGrades();
                saveCurrentStudentData(currentStudent);
                updateTotals();
            }
        });

        updateTotals();
    }

    public JPanel getPanel() {
        return buttonPanel;
    }

    public JComboBox<String> getStudentDropdown() {
        return studentDropdown;
    }

    private void loadStudentData(String student) {
        Object[][] data = studentGrades.get(student);
        DefaultTableModel model = tablePanel.getModel();
        model.setRowCount(0); // clear existing data
        for (Object[] row : data) {
            model.addRow(row.clone());
        }
        tablePanel.updateGrades();
    }

    private void saveCurrentStudentData(String student) {
        DefaultTableModel model = tablePanel.getModel();
        Object[][] data = new Object[model.getRowCount()][model.getColumnCount()];
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                data[i][j] = model.getValueAt(i, j);
            }
        }
        studentGrades.put(student, data);
    }

    private void updateTotals() {
        DefaultTableModel model = tablePanel.getModel();
        double totalMarks = 0;
        double totalPossible = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                double marks = Double.parseDouble(model.getValueAt(i, 1).toString());
                double total = Double.parseDouble(model.getValueAt(i, 2).toString());
                totalMarks += marks;
                totalPossible += total;
            } catch (Exception ignored) {}
        }
        totalLabelPanel.updateLabel(totalMarks, totalPossible);
    }
}
