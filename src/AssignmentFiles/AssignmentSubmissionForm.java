package AssignmentFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class AssignmentSubmissionForm {

    private JFrame frame;

    // Public constructor
    public AssignmentSubmissionForm() {
        // Frame setup
        frame = new JFrame("Assignments Submission");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(940, 550);
        frame.setLayout(null);

        // Set background color to green
        frame.getContentPane().setBackground(new Color(45, 136, 70));

        // Title
        JLabel titleLabel = new JLabel("Assignments Submission");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(320, 10, 400, 30);
        frame.add(titleLabel);

        // Course Selection
        JLabel courseLabel = new JLabel("Select Course:");
        courseLabel.setBounds(50, 60, 120, 25);
        frame.add(courseLabel);

        JComboBox<String> courseBox = new JComboBox<>(new String[]{"IT101", "CS201", "MATH123"});
        courseBox.setBounds(170, 60, 180, 25);
        frame.add(courseBox);

        // Existing Assignment Selection
        JLabel existingLabel = new JLabel("Existing Assignment/ s:");
        existingLabel.setBounds(480, 60, 170, 25);
        frame.add(existingLabel);

        JComboBox<String> assignmentBox = new JComboBox<>(new String[]{"Assignment 1", "Assignment 2"});
        assignmentBox.setBounds(660, 60, 180, 25);
        frame.add(assignmentBox);

        // Homework Title edit(false)
        JLabel titleLabel2 = new JLabel("Homework Title:");
        titleLabel2.setBounds(50, 110, 120, 25);
        frame.add(titleLabel2);

        JTextField titleField = new JTextField();
        titleField.setBounds(170, 110, 250, 25);
        titleField.setEditable(false);
        frame.add(titleField);

        // Due Date edit(false)
        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setBounds(480, 110, 80, 25);
        frame.add(dueDateLabel);

        JTextField dueDateField = new JTextField();
        dueDateField.setBounds(560, 110, 120, 25);
        dueDateField.setEditable(false);
        frame.add(dueDateField);

        // points edit(false)
        JLabel pointsLabel = new JLabel("Points: ");
        pointsLabel.setBounds(700, 110, 50, 25);
        frame.add(pointsLabel);

        JTextField pointsField = new JTextField();
        pointsField.setBounds(750, 110, 150, 25);
        pointsField.setEditable(false);
        frame.add(pointsField);

        // Assignment Area Label
        JLabel assignmentLabel = new JLabel("Write Your Assignment Here:");
        assignmentLabel.setBounds(50, 150, 300, 25);
        assignmentLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(assignmentLabel);

        // Assignment Text Area (editable)
        JTextArea assignmentArea = new JTextArea();
        assignmentArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(assignmentArea);
        scrollPane.setBounds(50, 180, 850, 260);
        frame.add(scrollPane);

        // Submit Button
        JButton submitButton = new JButton("Submit Button Here!");
        submitButton.setBounds(370, 460, 200, 35);
        frame.add(submitButton);

        // Sample Data Mapping
        HashMap<String, String[]> dataMap = new HashMap<>();
        // Sample Data Mapping (Homework Title, Due Date, Max points)
            dataMap.put("IT101-Assignment 1", new String[]{"Intro to Networking", "May 30, 2025", "100 pts"});
            dataMap.put("IT101-Assignment 2", new String[]{"Subnetting Basics", "June 5, 2025", "100 pts"});
            dataMap.put("CS201-Assignment 1", new String[]{"OOP in Java", "May 28, 2025", "100 pts"});
            dataMap.put("CS201-Assignment 2", new String[]{"Inheritance and Polymorphism", "June 1, 2025", "100 pts"});
            dataMap.put("MATH123-Assignment 1", new String[]{"Linear Equations", "May 25, 2025", "50 pts"});
            dataMap.put("MATH123-Assignment 2", new String[]{"Matrix Operations", "May 31, 2025", "50 pts"});

        // Event Listener to Update Fields
        ActionListener updateFields = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course = (String) courseBox.getSelectedItem();
                String assignment = (String) assignmentBox.getSelectedItem();
                String key = course + "-" + assignment;

                if (dataMap.containsKey(key)) {
                    String[] info = dataMap.get(key);
                    titleField.setText(info[0]);
                    dueDateField.setText(info[1]);
                    pointsField.setText(info[2]);
                } else {
                    titleField.setText("");
                    dueDateField.setText("");
                    pointsField.setText("");
                }
            }
        };

        // Bind listeners
        courseBox.addActionListener(updateFields);
        assignmentBox.addActionListener(updateFields);

        // Add ActionListener for submit
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course = (String) courseBox.getSelectedItem();
                String assignment = (String) assignmentBox.getSelectedItem();
                String title = titleField.getText();
                String dueDate = dueDateField.getText();
                String points = pointsField.getText();
                String assignmentText = assignmentArea.getText();

                if (assignmentText.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please write your assignment before submitting.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Show confirmation dialog
                JOptionPane.showMessageDialog(frame,
                    "Submitted!\n\nCourse: " + course +
                    "\nAssignment: " + assignment +
                    "\nTitle: " + title +
                    "\nDue Date: " + dueDate +
                    "\nPoints: " + points +
                    "\n\nYour answer:\n" + assignmentText,
                    "Submission Successful",
                    JOptionPane.INFORMATION_MESSAGE
                );

                // Optionally, clear the assignment area after submission
                assignmentArea.setText("");
            }
        });

        // Show frame
        frame.setVisible(true);
    }

    // Optional: method to get the JFrame if needed elsewhere
    public JFrame getFrame() {
        return frame;
    }

    // Main method for standalone run
    public static void main(String[] args) {
        new AssignmentSubmissionForm();
    }
}