package AssignmentFiles;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import DataSaving.FileHandle;

public class AssignmentManagementGUI {
    private JFrame frame;
    private JComboBox<String> courseCombo;
    private JTextField assignmentTitleField;
    private JTextArea descriptionArea;
    private JComboBox<String> yearCombo, monthCombo, dayCombo;
    private JTextField totalMarksField;
    private JButton addButton, clearButton, cancelButton;

    // These should be passed from your login/session logic
    private final StudentTeacher.Teacher teacher;
    private final Course.Course course;

    // Static fields for session management
    private static StudentTeacher.Teacher loggedInTeacher;
    private static Course.Course selectedCourse;

    public AssignmentManagementGUI(StudentTeacher.Teacher teacher, Course.Course course) {
        this.teacher = teacher;
        this.course = course;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("University of San Jose-Recoletos");
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
        totalMarksField = new JTextField("");
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

        // Listeners
        clearButton.addActionListener(e -> {
            assignmentTitleField.setText("");
            descriptionArea.setText("");
            yearCombo.setSelectedIndex(0);
            monthCombo.setSelectedIndex(0);
            dayCombo.setSelectedIndex(0);
            totalMarksField.setText("");
        });

        cancelButton.addActionListener(e -> frame.dispose());

        addButton.addActionListener(e -> {
            String title = assignmentTitleField.getText().trim();
            String description = descriptionArea.getText().trim();
            String year = (String) yearCombo.getSelectedItem();
            String month = (String) monthCombo.getSelectedItem();
            String day = (String) dayCombo.getSelectedItem();
            String totalMarks = totalMarksField.getText().trim();

            if (title.isEmpty() || description.isEmpty() || totalMarks.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int monthNum = Integer.parseInt(month.substring(0, 2));
                int dayNum = Integer.parseInt(day);
                java.time.LocalDate dueDate = java.time.LocalDate.of(Integer.parseInt(year), monthNum, dayNum);

                // Save assignment with teacher's name and assignment details
                AssignmentInfo assignment = new AssignmentInfo(title, description, dueDate);

                FileHandle fileHandle = new FileHandle();
                fileHandle.savePostedAssignment(teacher, course, assignment);

                JOptionPane.showMessageDialog(frame, "Assignment posted and saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                frame.dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving assignment: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }

    // Main method for launching the GUI
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Demo
        /*setLoggedInTeacher(new StudentTeacher.Teacher("Juan", "Dela Cruz", "T12345", "password123"));
        setSelectedCourse(new Course.Course("SCS101", "Programming 1"));*/

       
        StudentTeacher.Teacher teacher = getLoggedInTeacher();
        Course.Course course = getSelectedCourse();

        AssignmentManagementGUI gui = new AssignmentManagementGUI(teacher, course);
        gui.show();
    }

    
    public static void setLoggedInTeacher(StudentTeacher.Teacher teacher) {
        loggedInTeacher = teacher;
    }

    public static void setSelectedCourse(Course.Course course) {
        selectedCourse = course;
    }

    // Methods to get the teacher and course from session/static fields
    private static StudentTeacher.Teacher getLoggedInTeacher() {
        if (loggedInTeacher == null) {
            throw new IllegalStateException("No teacher is currently logged in. Please login first.");
        }
        return loggedInTeacher;
    }

    private static Course.Course getSelectedCourse() {
        if (selectedCourse == null) {
            throw new IllegalStateException("No course is currently selected. Please select a course first.");
        }
        return selectedCourse;
    }
}