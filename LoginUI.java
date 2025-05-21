

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("LMS");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);
            frame.setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new CardLayout());
            JPanel loginPanel = createLoginPanel(mainPanel);
            JPanel registrationPanel = createRegistrationForm(mainPanel);

            mainPanel.add(loginPanel, "Login");
            mainPanel.add(registrationPanel, "Register");

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }

    private static JPanel createLoginPanel(JPanel mainPanel) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Login to LMS", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;

        // ID
        gbc.gridy++;
        panel.add(new JLabel("ID Number:"), gbc);
        gbc.gridx = 1;
        JTextField userID = new JTextField(20);
        panel.add(userID, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField password = new JPasswordField(20);
        panel.add(password, gbc);

        // Buttons
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnPanel.setBackground(Color.WHITE);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Create Account");

        loginButton.setPreferredSize(new Dimension(130, 35));
        registerButton.setPreferredSize(new Dimension(130, 35));

        btnPanel.add(loginButton);
        btnPanel.add(registerButton);
        panel.add(btnPanel, gbc);

        registerButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "Register");
        });

        return panel;
    }

    private static JPanel createRegistrationForm(JPanel mainPanel) {
        JPanel regPanel = new JPanel();
        regPanel.setBackground(Color.WHITE);
        regPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        GridBagLayout layout = new GridBagLayout();
        regPanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel title = new JLabel("Student Registration", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        regPanel.add(title, gbc);
        gbc.gridwidth = 1;

        // Student ID
        gbc.gridy++;
        regPanel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        JTextField idField = new JTextField(20);
        regPanel.add(idField, gbc);

        // Name
        gbc.gridx = 0;
        gbc.gridy++;
        regPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        JTextField nameField = new JTextField(20);
        regPanel.add(nameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy++;
        regPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        regPanel.add(passwordField, gbc);

        // Degree
        gbc.gridx = 0;
        gbc.gridy++;
        regPanel.add(new JLabel("Degree Program:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> degreeCombo = new JComboBox<>(getDegrees());
        degreeCombo.setPreferredSize(new Dimension(300, 30));
        regPanel.add(degreeCombo, gbc);

        // Year
        gbc.gridx = 0;
        gbc.gridy++;
        regPanel.add(new JLabel("Year Level:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> yearCombo = new JComboBox<>(new String[]{"Select Year", "1", "2", "3", "4"});
        yearCombo.setPreferredSize(new Dimension(300, 30));
        regPanel.add(yearCombo, gbc);

        // Register Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton registerBtn = new JButton("Register");
        registerBtn.setPreferredSize(new Dimension(150, 35));
        regPanel.add(registerBtn, gbc);

        // Login Link
        gbc.gridy++;
        JLabel loginLink = new JLabel("<html><u>Already have an account? Log in</u></html>");
        loginLink.setForeground(Color.BLUE.darker());
        loginLink.setFont(new Font("SansSerif", Font.PLAIN, 14));
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        regPanel.add(loginLink, gbc);

        loginLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Login");
            }
        });

        return regPanel;
    }

    private static String[] getDegrees() {
        return new String[]{
            "Select Degree",
            "Bachelor of Arts in Communication", "Bachelor of Arts in Marketing Communication",
            "Bachelor of Arts in Journalism", "Bachelor of Arts in English Language Studies",
            "Bachelor of Arts in International Studies", "Bachelor of Arts in Political Science",
            "Bachelor of Arts in Philosophy", "Bachelor of Science in Biology major in Medical Biology",
            "Bachelor of Science in Biology major in Microbiology", "Bachelor of Science in Psychology",
            "Bachelor of Library and Information Science", "Bachelor of Science in Accountancy",
            "Bachelor of Science in Management Accounting", "Bachelor of Science in Civil Engineering",
            "Bachelor of Science in Computer Engineering", "Bachelor of Science in Electrical Engineering",
            "Bachelor of Science in Electronics Engineering", "Bachelor of Science in Industrial Engineering",
            "Bachelor of Science in Mechanical Engineering", "Bachelor of Science in Business Administration major in Financial Management",
            "Bachelor of Science in Business Administration major in Operations Management",
            "Bachelor of Science in Business Administration major in Human Resource Development Management",
            "Bachelor of Science in Business Administration major in Marketing Management",
            "Bachelor of Science in Entrepreneurship", "Bachelor of Science in Hospitality Management",
            "Bachelor of Science in Hospitality Management major in Food and Beverage",
            "Bachelor of Science in Tourism Management", "Bachelor of Science in Computer Science",
            "Bachelor of Science in Entertainment and Multimedia Computing", "Bachelor of Science in Information Technology",
            "Bachelor of Science in Information Systems", "Bachelor of Elementary Education",
            "Bachelor of Early Childhood Education", "Bachelor of Physical Education",
            "Bachelor of Secondary Education Major in English", "Bachelor of Secondary Education Major in Filipino",
            "Bachelor of Secondary Education Major in Mathematics", "Bachelor of Secondary Education Major in Science",
            "Bachelor of Special Needs Education-Generalist", "Bachelor of Special Needs Education major in Early Childhood Education",
            "Bachelor of Special Needs Education major in Elementary School Teaching", "Bachelor of Science in Nursing",
            "Bachelor of Science in Medical Technology / Medical Laboratory Science"
        };
    }
}
