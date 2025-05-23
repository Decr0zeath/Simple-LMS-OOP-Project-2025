package mypackage1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// --- Action interface for button 
interface WindowAction {
    void execute();
}

// --- CourseWindowView
class CourseWindowView {
    JFrame frame;
    JComboBox<String> courseCombo;
    JTextField assignmentTitleField;
    JTextArea descriptionArea;
    JComboBox<String> yearCombo, monthCombo, dayCombo;
    JTextField totalMarksField;
    JButton addButton, clearButton, cancelButton;

    public CourseWindowView() {
        frame = new JFrame("Course Overview - Add Assignment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 200, 40, 200));
        mainPanel.setBackground(new Color(45, 88, 56));

        JLabel titleLabel = new JLabel("Add Assignment to Course", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(30));

        JPanel coursePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JLabel courseLabel = new JLabel("Select Course:");
        courseLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        String[] courses = {
            "Programming 1 - (SCS101)",
            "Web Development - (SCS102)",
            "Digital Visual Art - (SCS103)",
            "Introduction to Cyber Security - (SCS104)",
            "Data Structures and Algorithm - (SCS105)"
        };
        courseCombo = new JComboBox<>(courses);
        courseCombo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        courseCombo.setPreferredSize(new Dimension(500, 35));
        coursePanel.setBackground(new Color(45, 88, 56));
        coursePanel.add(courseLabel);
        coursePanel.add(courseCombo);
        mainPanel.add(coursePanel);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JLabel assignmentTitleLabel = new JLabel("Assignment Title:");
        assignmentTitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        assignmentTitleField = new JTextField();
        assignmentTitleField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        assignmentTitleField.setPreferredSize(new Dimension(500, 35));
        titlePanel.setBackground(new Color(45, 88, 56));
        titlePanel.add(assignmentTitleLabel);
        titlePanel.add(assignmentTitleField);
        mainPanel.add(titlePanel);

        JPanel descPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        descriptionArea = new JTextArea(5, 40);
        descriptionArea.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        descScroll.setPreferredSize(new Dimension(500, 100));
        descPanel.setBackground(new Color(45, 88, 56));
        descPanel.add(descriptionLabel);
        descPanel.add(descScroll);
        mainPanel.add(descPanel);

        JPanel dueDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        String[] years = {"2025"};
        yearCombo = new JComboBox<>(years);
        yearCombo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        String[] months = {
            "01 - January", "02 - February", "03 - March", "04 - April", "05 - May", "06 - June",
            "07 - July", "08 - August", "09 - September", "10 - October", "11 - November", "12 - December"
        };
        monthCombo = new JComboBox<>(months);
        monthCombo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1);
        }
        dayCombo = new JComboBox<>(days);
        dayCombo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        dueDatePanel.setBackground(new Color(45, 88, 56));
        dueDatePanel.add(dueDateLabel);
        dueDatePanel.add(yearCombo);
        dueDatePanel.add(monthCombo);
        dueDatePanel.add(dayCombo);
        mainPanel.add(dueDatePanel);

        JPanel marksPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JLabel totalMarksLabel = new JLabel("Total Marks:");
        totalMarksLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        totalMarksField = new JTextField("100");
        totalMarksField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        totalMarksField.setPreferredSize(new Dimension(100, 35));
        marksPanel.setBackground(new Color(45, 88, 56));
        marksPanel.add(totalMarksLabel);
        marksPanel.add(totalMarksField);
        mainPanel.add(marksPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        addButton = new JButton("Add Assignment");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        addButton.setBackground(new Color(33, 150, 243));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 20));

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 20));

        buttonPanel.setBackground(new Color(45, 88, 56));
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);

        frame.setContentPane(mainPanel);
    }

    public void show() {
        frame.setVisible(true);
    }
}

class CourseWindowController {
    private final CourseWindowView view;
    private final WindowAction onAddAssignment;

    public CourseWindowController(CourseWindowView view, WindowAction onAddAssignment) {
        this.view = view;
        this.onAddAssignment = onAddAssignment;
        attachListeners();
    }

    private void attachListeners() {
        view.clearButton.addActionListener(e -> {
            view.assignmentTitleField.setText("");
            view.descriptionArea.setText("");
            view.yearCombo.setSelectedIndex(0);
            view.monthCombo.setSelectedIndex(0);
            view.dayCombo.setSelectedIndex(0);
            view.totalMarksField.setText("");
        });
        view.cancelButton.addActionListener(e -> view.frame.dispose());
        view.addButton.addActionListener(e -> {
            view.frame.dispose();
            onAddAssignment.execute();
        });
    }
}

// --- GUIDemo:
class GUIDemo {
    JFrame frame;

    public GUIDemo() {
        frame = new JFrame("GUIDemo Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        JLabel label = new JLabel("Welcome to GUIDemo!", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 28));
        frame.add(label);
        frame.setVisible(true);
    }
}

// --- Main class
public class Main {
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        CourseWindowView view = new CourseWindowView();
        new CourseWindowController(view, () -> new GUIDemo());
        view.show();
    }
}