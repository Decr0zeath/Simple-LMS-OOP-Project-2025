package LoginScreen;

import LoginScreen.LoginPanel.RoundedButtonUI;
import StudentTeacher.Student;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.text.*;

public class RegistrationPanel extends JPanel {
    private JTextField userID;
    private JPasswordField password;
    private JPasswordField confirmPassword;
    private JLabel messageLabel;
    private CardLayout parentLayout;
    private JPanel parentPanel;

    public RegistrationPanel(JPanel parentPanel, CardLayout parentLayout) {
        this.parentPanel = parentPanel;
        this.parentLayout = parentLayout;
        initComponents();
    }

    private void initComponents() {
        Color darkGreen = new Color(69, 136, 70);
        setBackground(darkGreen);
        setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Logo
        ImageIcon originalIcon = loadImage("lms-logo.png");
        if (originalIcon != null) {
            Image scaledImage = originalIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(logoLabel, gbc);
        }

        // Title
        gbc.gridy = 1;
        JLabel title = new JLabel("Register Account", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Account ID
        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel idLabel = new JLabel("Account ID:");
        idLabel.setForeground(Color.WHITE);
        add(idLabel, gbc);

        gbc.gridx = 1;
        userID = new JTextField(20);
        ((AbstractDocument) userID.getDocument()).setDocumentFilter(new NumericFilter());
        add(userID, gbc);

        // Password
        gbc.gridy = 3;
        gbc.gridx = 0;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        password = new JPasswordField(20);
        add(password, gbc);

        // Confirm Password
        gbc.gridy = 4;
        gbc.gridx = 0;
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setForeground(Color.WHITE);
        add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        confirmPassword = new JPasswordField(20);
        add(confirmPassword, gbc);
        confirmPassword.addActionListener(e -> onRegister());

        // Message Label
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(messageLabel, gbc);

        // Buttons
        gbc.gridy = 6;
        gbc.gridx = 0;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnPanel.setBackground(darkGreen);

        JButton registerButton = createRoundedButton("Register", new Color(0, 100, 0), Color.WHITE);
        JButton backButton = createRoundedButton("Back to Login", new Color(0, 100, 0), Color.WHITE);

        registerButton.setPreferredSize(new Dimension(140, 40));
        backButton.setPreferredSize(new Dimension(140, 40));

        btnPanel.add(registerButton);
        btnPanel.add(backButton);
        add(btnPanel, gbc);

        // Listeners
        registerButton.addActionListener(e -> onRegister());
        backButton.addActionListener(e -> onBackToLogin());
    }

    private JButton createRoundedButton(String text, Color bgColor, Color fgColor) {
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

    private ImageIcon loadImage(String imageName) {
        URL imageUrl = getClass().getClassLoader().getResource("resources/" + imageName);
        return imageUrl != null ? new ImageIcon(imageUrl) : null;
    }

    private void onRegister() {
        String id = userID.getText().trim();
        String pass = new String(password.getPassword()).trim();
        String confirmPass = new String(confirmPassword.getPassword()).trim();

        if (id.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
            showMessage("Please fill out all fields.", Color.RED);
            return;
        }

        if (!pass.equals(confirmPass)) {
            showMessage("Passwords do not match.", Color.RED);
            return;
        }

        if (!id.matches("\\d+")) {
            showMessage("Account ID must be numeric.", Color.RED);
            return;
        }

        String role = LMSApp.determineRole(id);
        if (role.equals("Invalid")) {
            showMessage("Invalid Account ID length.", Color.RED);
            return;
        }

        String hashedPassword = LMSApp.hashPassword(pass);
        Student student = new Student(id, hashedPassword, "Unknown", id + "@student.lms", "Undeclared", 1);


        try {
            LMSApp.saveStudent(student);
            showMessage("Registration successful! Please login.", new Color(0, 255, 0));
            clearFields();

            Timer timer = new Timer(1500, e -> onBackToLogin());
            timer.setRepeats(false);
            timer.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            showMessage("Failed to save student data.", Color.RED);
        }
    }

    private void onBackToLogin() {
        parentLayout.show(parentPanel, "Login");
        clearFields();
        showMessage(" ", Color.RED);
    }

    private void clearFields() {
        userID.setText("");
        password.setText("");
        confirmPassword.setText("");
    }

    private void showMessage(String message, Color color) {
        messageLabel.setText(message);
        messageLabel.setForeground(color);
    }

    // DocumentFilter for numeric input only
    static class NumericFilter extends DocumentFilter {
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
}
