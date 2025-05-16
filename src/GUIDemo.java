
import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;
///import java.awt.geom.*;

public class GUIDemo {

    // Custom JLabel with stroke effect
    static class StrokeLabel extends JLabel {
        private Color strokeColor = Color.white;
        private int strokeWidth = 2;

        public StrokeLabel(String text) {
            super(text);
            setForeground(Color.black);
            setFont(new Font("SansSerif", Font.BOLD, 14));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setFont(getFont());
            String text = getText();
            FontMetrics fm = g2.getFontMetrics();
            int x = 0;
            int y = fm.getAscent();

            // Draw stroke
            g2.setColor(strokeColor);
            g2.setStroke(new BasicStroke(strokeWidth));
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx != 0 || dy != 0) {
                        g2.drawString(text, x + dx, y + dy);
                    }
                }
            }
            // Draw main text
            g2.setColor(getForeground());
            g2.drawString(text, x, y);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Assignment Submission");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400   , 350);
        frame.setLocationRelativeTo(null);
        // frame.isResizable(false);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        // Set background color to #458846 (RGB: 69, 136, 70)
        Color darkGreen = new Color(69, 136, 70);
        panel.setBackground(darkGreen);

        // Course selection
        JPanel coursePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        coursePanel.setBackground(darkGreen);
        JLabel courseLabel = new StrokeLabel("Select Course:");
        String[] courses = {"Math", "Science", "History", "Computer Science"};
        JComboBox<String> courseComboBox = new JComboBox<>(courses);
        courseComboBox.setPreferredSize(new Dimension(180, 25));
        coursePanel.add(courseLabel);
        coursePanel.add(courseComboBox);

        // Assignment title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(darkGreen);
        JLabel titleLabel = new StrokeLabel("Assignment Title:");
        JTextField assignmentField = new JTextField();
        assignmentField.setPreferredSize(new Dimension(180, 25));
        assignmentField.setBackground(new Color(245, 245, 220));
        assignmentField.setForeground(Color.black);
        assignmentField.setFont(new Font("SansSerif", Font.BOLD, 14));
        titlePanel.add(titleLabel);
        titlePanel.add(assignmentField);

        // Assignment text
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textPanel.setBackground(darkGreen);
        JLabel textLabel = new StrokeLabel("Assignment Text:");
        JTextArea descriptionArea = new JTextArea(4, 18);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBackground(new Color(245, 245, 220));
        descriptionArea.setForeground(Color.black);
        descriptionArea.setFont(new Font("SansSerif", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setPreferredSize(new Dimension(180, 70));
        textPanel.add(textLabel);
        textPanel.add(scrollPane);

        // Submit button
        JButton submitButton = new JButton("Submit Assignment");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Confirmation label
        JLabel confirmationLabel = new StrokeLabel("");
        confirmationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add all panels/components to main panel
        panel.add(coursePanel);
        panel.add(titlePanel);
        panel.add(textPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(submitButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(confirmationLabel);

        // Button action
        submitButton.addActionListener(e -> {
            String selectedCourse = (String) courseComboBox.getSelectedItem();
            String assignmentTitle = assignmentField.getText().trim();
            String assignmentText = descriptionArea.getText().trim();

            if (assignmentTitle.isEmpty()) {
                confirmationLabel.setText("Assignment title cannot be blank!");
                return;
            }
            if (assignmentText.isEmpty()) {
                confirmationLabel.setText("Assignment text cannot be blank!");
                return;
            }

            confirmationLabel.setText("Assignment submitted to " + selectedCourse + "!");
            System.out.println("Submission Received:");
            System.out.println("Course: " + selectedCourse);
            System.out.println("Assignment Title: " + assignmentTitle);
            System.out.println("Assignment Text: " + assignmentText);
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
