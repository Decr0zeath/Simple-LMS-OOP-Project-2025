package LoginScreen;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import Authentication.authenticationLogic;
import DataSaving.FileHandle;
import StudentTeacher.Student; 

public class RegistrationPanel extends JPanel {

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField accountIdField;
    private JPasswordField passwordField;
    private JPasswordField retypePasswordField;
    private JComboBox<String> degreeComboBox;
    private JComboBox<String> yearComboBox;
    private CardLayout parentLayout;
    private JPanel parentPanel;

    private FileHandle fileHandle = new FileHandle();

    public RegistrationPanel(JPanel parentPanel, CardLayout parentLayout) {
        this.parentPanel = parentPanel;
        this.parentLayout = parentLayout;
        initComponents();
    }

    private void initComponents() {
        Color darkGreen = new Color(69, 136, 70);
        setBackground(darkGreen);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setPreferredSize(new Dimension(550, 600));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        int y = 0;

        // Title
        JLabel title = new JLabel("Student Registration", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        gbc.insets = new Insets(10, 4, 10, 4);
        gbc.gridx = 0;
        gbc.gridy = y++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(title, gbc);
        gbc.gridwidth = 1;

        // Input fields labels
        String[] labels = {
            "First Name:", "Last Name:", "Account ID:", "Password:",
            "Retype Password:", "Degree Program:", "Year Level:"
        };

        firstNameField = new JTextField(20);
        ((AbstractDocument) firstNameField.getDocument()).setDocumentFilter(new LettersOnlyFilter());
        lastNameField = new JTextField(20);
        ((AbstractDocument) lastNameField.getDocument()).setDocumentFilter(new LettersOnlyFilter());
        accountIdField = new JTextField(20);
        ((AbstractDocument) accountIdField.getDocument()).setDocumentFilter(new NumberOnlyFilter());
        passwordField = new JPasswordField(20);
        retypePasswordField = new JPasswordField(20);
        degreeComboBox = new JComboBox<>(getDegrees());
        yearComboBox = new JComboBox<>(new String[]{"Select Year", "1", "2", "3", "4"});

        Component[] inputs = {
            firstNameField, lastNameField, accountIdField,
            passwordField, retypePasswordField, degreeComboBox, yearComboBox
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.insets = new Insets(6, 4, 6, 4);
            gbc.gridx = 0;
            gbc.gridy = y;
            gbc.anchor = GridBagConstraints.WEST;
            JLabel label = new JLabel(labels[i]);
            label.setForeground(Color.WHITE);
            add(label, gbc);

            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(inputs[i], gbc);
            y++;
        }

        // Buttons Panel with improved UI
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(darkGreen);

        JButton registerBtn = createRoundedButton("Register", new Color(0, 128, 0), Color.WHITE);
        JButton cancelBtn = createRoundedButton("Cancel", new Color(0, 100, 0), Color.WHITE);

        registerBtn.setPreferredSize(new Dimension(140, 40));
        cancelBtn.setPreferredSize(new Dimension(140, 40));

        buttonPanel.add(registerBtn);
        buttonPanel.add(cancelBtn);

        gbc.insets = new Insets(20, 4, 4, 4);
        gbc.gridx = 0;
        gbc.gridy = y++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);

        // Button actions
        cancelBtn.addActionListener(e -> {
            clearFields(); // Clear all fields when going back
            parentLayout.show(parentPanel, "Login");
        });
        registerBtn.addActionListener(e -> onRegister());
    }

    private void onRegister() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String studentId = accountIdField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String retypePassword = new String(retypePasswordField.getPassword()).trim();
        String degree = (String) degreeComboBox.getSelectedItem();
        String yearLevel = (String) yearComboBox.getSelectedItem();

        // Validation
        if (firstName.isEmpty() || lastName.isEmpty() || studentId.isEmpty() ||
            password.isEmpty() || retypePassword.isEmpty() ||
            degree.equals("Select Degree") || yearLevel.equals("Select Year")) {
            JOptionPane.showMessageDialog(this, "Please complete all fields.",
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if passwords match
        if (!password.equals(retypePassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.",
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate student ID format (must be 10 digits)
        if (!studentId.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this,
                "Student ID must be 10 digits long.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Hash the password
            String hashedPassword = authenticationLogic.handleRegistrationPasswordHash(password);

            // Create new Student object (not StudentInfo)
            Student newStudent = new Student(
                firstName,
                lastName,
                studentId,
                hashedPassword,
                degree,
                Integer.parseInt(yearLevel)
            );

            // Save student using FileHandle
            FileHandle.fileCreate();
            fileHandle.saveStudent(newStudent);

            JOptionPane.showMessageDialog(this,
                "Registration successful! Please login with your credentials.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

            // Clear fields and return to login
            clearFields();
            parentLayout.show(parentPanel, "Login");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "An error occurred during registration: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        accountIdField.setText("");
        passwordField.setText("");
        retypePasswordField.setText("");
        degreeComboBox.setSelectedIndex(0);
        yearComboBox.setSelectedIndex(0);
    }

    private String[] getDegrees() {
        return new String[]{
            "Select Degree",
            "BS in Computer Science",
            "BS in Entertainment & Multimedia Computing",
            "BS in Information Technology",
            "BS in Information Systems"
        };
    }

    // DocumentFilter for numeric input only
    static class NumberOnlyFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string.matches("\\d+")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text.matches("\\d+")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    // DocumentFilter for letters only
    static class LettersOnlyFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (string.matches("[a-zA-Z]+")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (text.matches("[a-zA-Z]+")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    private static JButton createRoundedButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setUI(new RoundedButtonUI(bgColor, fgColor));
        return button;
    }

    private static class RoundedButtonUI extends javax.swing.plaf.basic.BasicButtonUI {
        private final Color background;
        private final Color foreground;

        RoundedButtonUI(Color bg, Color fg) {
            background = bg;
            foreground = fg;
        }

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            AbstractButton b = (AbstractButton) c;
            b.setOpaque(false);
            b.setForeground(foreground);
            b.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (b.getModel().isPressed()) {
                g2.setColor(background.darker());
            } else if (b.getModel().isRollover()) {
                g2.setColor(background.brighter());
            } else {
                g2.setColor(background);
            }

            g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 20, 20);

            // Draw the button text
            String text = b.getText();
            if (text != null && !text.isEmpty()) {
                g2.setColor(foreground);
                FontMetrics fm = g2.getFontMetrics();
                Rectangle r = b.getBounds();
                int x = (r.width - fm.stringWidth(text)) / 2;
                int y = (r.height + fm.getAscent()) / 2 - 2;
                g2.drawString(text, x, y);
            }

            g2.dispose();
        }
    }
}
