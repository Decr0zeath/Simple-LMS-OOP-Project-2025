package LoginScreen;

import StudentTeacher.Student;
import java.awt.*;
import javax.swing.*;

public class RegistrationPanel extends JPanel {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField accountIDField;
    private JPasswordField passwordField;
    private JTextField degreeField;
    private JTextField yearField;

    private JLabel messageLabel;

    private CardLayout parentLayout;
    private JPanel parentPanel;

    public RegistrationPanel(JPanel parentPanel, CardLayout parentLayout) {
        this.parentPanel = parentPanel;
        this.parentLayout = parentLayout;
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(69, 136, 70));
        setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // First Name
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setForeground(Color.WHITE);
        add(firstNameLabel, gbc);

        gbc.gridx = 1;
        firstNameField = new JTextField(20);
        add(firstNameField, gbc);

        // Last Name
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setForeground(Color.WHITE);
        add(lastNameLabel, gbc);

        gbc.gridx = 1;
        lastNameField = new JTextField(20);
        add(lastNameField, gbc);

        // Account ID
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel accountIDLabel = new JLabel("Account ID:");
        accountIDLabel.setForeground(Color.WHITE);
        add(accountIDLabel, gbc);

        gbc.gridx = 1;
        accountIDField = new JTextField(20);
        add(accountIDField, gbc);

        // Password
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        add(passwordField, gbc);

        // Degree
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel degreeLabel = new JLabel("Degree:");
        degreeLabel.setForeground(Color.WHITE);
        add(degreeLabel, gbc);

        gbc.gridx = 1;
        degreeField = new JTextField(20);
        add(degreeField, gbc);

        // Year
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setForeground(Color.WHITE);
        add(yearLabel, gbc);

        gbc.gridx = 1;
        yearField = new JTextField(20);
        add(yearField, gbc);

        // Message Label
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(messageLabel, gbc);

        // Buttons Panel
        gbc.gridy++;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnPanel.setBackground(new Color(69, 136, 70));

        JButton registerBtn = new JButton("Register");
        JButton backBtn = new JButton("Back to Login");

        registerBtn.setPreferredSize(new Dimension(140, 40));
        backBtn.setPreferredSize(new Dimension(140, 40));

        btnPanel.add(registerBtn);
        btnPanel.add(backBtn);
        add(btnPanel, gbc);

        registerBtn.addActionListener(e -> onRegister());
        backBtn.addActionListener(e -> onBackToLogin());
    }

    private void onRegister() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String accountID = accountIDField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String degree = degreeField.getText().trim();
        String yearStr = yearField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || accountID.isEmpty() || 
            password.isEmpty() || degree.isEmpty() || yearStr.isEmpty()) {
            showMessage("Please fill in all fields.", Color.RED);
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            showMessage("Year must be a number.", Color.RED);
            return;
        }

        Student newStudent = new Student(firstName, lastName, accountID, password, degree, year);

        LMSApp.saveStudentWithHashedPassword(newStudent);

        showMessage("Registration successful! Please login.", new Color(0, 128, 0));
        clearFields();
    }

    private void onBackToLogin() {
        parentLayout.show(parentPanel, "Login");
        clearFields();
        showMessage(" ", Color.RED);
    }

    private void showMessage(String message, Color color) {
        messageLabel.setText(message);
        messageLabel.setForeground(color);
    }

    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        accountIDField.setText("");
        passwordField.setText("");
        degreeField.setText("");
        yearField.setText("");
    }
}
