import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class RegistrationPanel extends JPanel {

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField accountIdField;
    private JPasswordField passwordField;
    private JComboBox<String> degreeComboBox;
    private JComboBox<String> yearComboBox;
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
            "Degree Program:", "Year Level:"
        };

        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        accountIdField = new JTextField(20);
        ((AbstractDocument) accountIdField.getDocument()).setDocumentFilter(new NumberOnlyFilter());
        passwordField = new JPasswordField(20);
        degreeComboBox = new JComboBox<>(getDegrees());
        yearComboBox = new JComboBox<>(new String[]{"Select Year", "1", "2", "3", "4"});

        Component[] inputs = {
            firstNameField, lastNameField, accountIdField,
            passwordField, degreeComboBox, yearComboBox
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
        cancelBtn.addActionListener(e -> parentLayout.show(parentPanel, "Login"));

        registerBtn.addActionListener(e -> onRegister());
    }

    private void onRegister() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String studentId = accountIdField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String degree = (String) degreeComboBox.getSelectedItem();
        String yearLevel = (String) yearComboBox.getSelectedItem();

        if (firstName.isEmpty() || lastName.isEmpty() || studentId.isEmpty() ||
            password.isEmpty() || degree.equals("Select Degree") || yearLevel.equals("Select Year")) {
            JOptionPane.showMessageDialog(this, "Please complete all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Congratulations, you are registered!");
        parentLayout.show(parentPanel, "Login");
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

    // Same RoundedButtonUI class from LoginPanel for consistent button style
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
            g2.setColor(foreground);

            FontMetrics fm = g2.getFontMetrics();
            Rectangle r = b.getBounds();
            String text = b.getText();
            int x = (r.width - fm.stringWidth(text)) / 2;
            int y = (r.height + fm.getAscent()) / 2 - 2;
            g2.drawString(text, x, y);

            g2.dispose();
        }
    }
}
