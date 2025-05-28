package GradeAssign;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ButtonPanel {
    private JPanel buttonPanel;
    private JComboBox<String> studentDropdown;

    private TablePanel tablePanel;
    private TotalLabelPanel totalLabelPanel;
    private StudentGradeManager gradeManager;

    private String currentStudent;

    public ButtonPanel(TablePanel tablePanel, TotalLabelPanel totalLabelPanel, StudentGradeManager gradeManager) {
        this.tablePanel = tablePanel;
        this.totalLabelPanel = totalLabelPanel;
        this.gradeManager = gradeManager;

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        buttonPanel.setBackground(new Color(0,128,0));

        studentDropdown = new JComboBox<>(gradeManager.getAllStudents());
        studentDropdown.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(studentDropdown);

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
                int row = e.getFirstRow();
                DefaultTableModel model = tablePanel.getModel();
                Object value = model.getValueAt(row, 1);
                Object total = model.getValueAt(row, 2);

                if (InputValidator.isValidNumber(value.toString()) && InputValidator.isValidNumber(total.toString())) {
                    tablePanel.updateGrades();
                    saveCurrentStudentData(currentStudent);
                    updateTotals();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Invalid input. Please enter numeric values only.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    model.setValueAt(0, row, 1); 
                }
            }
        });

        updateTotals();
    }

    public JPanel getPanel() {
        return buttonPanel;
    }

    private void loadStudentData(String student) {
        Object[][] data = gradeManager.getGrades(student);
        DefaultTableModel model = tablePanel.getModel();
        model.setRowCount(0);
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
        gradeManager.updateGrades(student, data);
    }

    private void updateTotals() {
        DefaultTableModel model = tablePanel.getModel();
        double totalMarks = 0, totalPossible = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                totalMarks += Double.parseDouble(model.getValueAt(i, 1).toString());
                totalPossible += Double.parseDouble(model.getValueAt(i, 2).toString());
            } catch (Exception ignored) {}
        }
        totalLabelPanel.updateLabel(totalMarks, totalPossible);
    }
}

