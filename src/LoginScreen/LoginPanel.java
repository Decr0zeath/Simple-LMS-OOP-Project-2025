package LoginScreen;

import Dashboard.Dashboard;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.text.*;

public class LoginPanel extends JPanel {
    private JTextField userID;
    private JPasswordField password;
    private JLabel messageLabel;
    private CardLayout parentLayout;
    private JPanel parentPanel;

    public LoginPanel(JPanel parentPanel, CardLayout parentLayout) {
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

        gbc.gridy = 1;
        JLabel title = new JLabel("Login to LMS", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel idLabel = new JLabel("Account ID:");
        idLabel.setForeground(Color.WHITE);
        add(idLabel, gbc);

        gbc.gridx = 1;
        userID = new JTextField(20);
        ((AbstractDocument) userID.getDocument()).setDocumentFilter(new NumericFilter());
        add(userID, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        password = new JPasswordField(20);
        add(password, gbc);
        password.addActionListener(e -> onLogin());

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(messageLabel, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnPanel.setBackground(darkGreen);

        JButton loginButton = createRoundedButton("Login", new Color(0, 100, 0), Color.WHITE);
        JButton registerButton = createRoundedButton("Create Account", new Color(0, 100, 0), Color.WHITE);

        loginButton.setPreferredSize(new Dimension(140, 40));
        registerButton.setPreferredSize(new Dimension(140, 40));

        btnPanel.add(loginButton);
        btnPanel.add(registerButton);
        add(btnPanel, gbc);

        loginButton.addActionListener(e -> onLogin());
        registerButton.addActionListener(e -> onSwitchToRegister());
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

    private void onLogin() {
        String userId = userID.getText().trim();
        String pass = new String(password.getPassword()).trim();

        if (userId.isEmpty() && pass.isEmpty()) {
            showMessage("<html><center>Please enter your <b>Account ID</b> and <b>Password</b>.</center></html>", Color.RED);
            return;
        }
        if (userId.isEmpty()) {
            showMessage("Please enter your Account ID.", Color.RED);
            return;
        }
        if (pass.isEmpty()) {
            showMessage("Please enter your Password.", Color.RED);
            return;
        }

        String result = LMSApp.authenticate(userId, pass);

        if (result.equals("Invalid")) {
            showMessage("Invalid ID number or password. Please try again.", Color.RED);
            return;
        }

        showMessage("Login successful! Redirecting...", new Color(0, 255, 0));
        Timer timer = new Timer(1000, evt -> {
            new Dashboard();
            SwingUtilities.getWindowAncestor(this).dispose();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void clearFields() {
        userID.setText("");
        password.setText("");
    }

    private void onSwitchToRegister() {
        parentLayout.show(parentPanel, "Register");
        clearFields();
        showMessage(" ", Color.RED);
    }

    private void showMessage(String message, Color color) {
        messageLabel.setText(message);
        messageLabel.setForeground(color);
    }

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

    // ✅ PUBLIC so other classes like RegistrationPanel can use it
    public static class RoundedButtonUI extends javax.swing.plaf.basic.BasicButtonUI {
        private final Color background;
        private final Color foreground;

        public RoundedButtonUI(Color bg, Color fg) {
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

            g2.setColor(b.getModel().isPressed() ? background.darker() :
                        b.getModel().isRollover() ? background.brighter() :
                        background);

            g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 20, 20);
            g2.setColor(foreground);

            FontMetrics fm = g2.getFontMetrics();
            String text = b.getText();
            int x = (b.getWidth() - fm.stringWidth(text)) / 2;
            int y = (b.getHeight() + fm.getAscent()) / 2 - 2;
            g2.drawString(text, x, y);
            g2.dispose();
        }
    }
}
