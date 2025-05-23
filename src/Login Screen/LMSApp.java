import javax.swing.*;

public class LMSApp {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public LMSApp() {
        initUI();
    }

    private void initUI() {
        frame = new JFrame("LMS");
        frame.setSize(550, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Set icon if exists
        ImageIcon icon = new ImageIcon("C:\\Users\\oopa21\\Documents\\logo.png");
        if (icon.getIconWidth() != -1) {
            frame.setIconImage(icon.getImage());
        }

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        LoginPanel loginPanel = new LoginPanel(mainPanel, cardLayout);
        RegistrationPanel registrationPanel = new RegistrationPanel(mainPanel, cardLayout);

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(registrationPanel, "Register");

        frame.add(mainPanel);
        frame.setVisible(true);

        cardLayout.show(mainPanel, "Login");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LMSApp::new);
    }
}
