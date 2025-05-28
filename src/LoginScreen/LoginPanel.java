package LoginScreen;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.*;

public class LoginPanel extends JPanel {

    private JTextField accountIDField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    public LoginPanel() {
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Account ID:"));
        accountIDField = new JTextField();
        add(accountIDField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        add(loginButton);

        statusLabel = new JLabel("");
        add(statusLabel);

        loginButton.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String accountID = accountIDField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (accountID.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please enter both Account ID and Password.");
            return;
        }

        try {
            if (authenticateUser(accountID, password)) {
                statusLabel.setText("Login successful!");
                // Proceed to next screen or functionality here
            } else {
                statusLabel.setText("Invalid Account ID or Password.");
            }
        } catch (IOException ex) {
            statusLabel.setText("Error reading user data.");
            ex.printStackTrace();
        }
    }

    // Authenticate user by checking hashed password in file
    private boolean authenticateUser(String accountID, String password) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("newStudent.txt"));
        String line;

        String hashedInputPassword = hashPassword(password);

        while ((line = reader.readLine()) != null) {
            // Assuming file format:
            // firstName,lastName,accountID,hashedPassword,degree,year,role
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                String fileAccountID = parts[2];
                String fileHashedPassword = parts[3];

                if (fileAccountID.equals(accountID) && fileHashedPassword.equals(hashedInputPassword)) {
                    reader.close();
                    return true; // User authenticated
                }
            }
        }
        reader.close();
        return false; // No matching user found
    }

    // SHA-256 hashing (same as LMSApp)
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return password;  // fallback - not secure
        }
    }
}
