package LoginScreen;

import java.awt.CardLayout;
import java.net.URL;
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

        // Set the icon image for the frame (window)
        ImageIcon icon = loadImage("lms-logo.png");
        if (icon != null) {
            frame.setIconImage(icon.getImage());
        } else {
            System.out.println("Logo image for frame not found.");
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

    private ImageIcon loadImage(String imageName) {
        URL imageUrl = getClass().getClassLoader().getResource("resources/" + imageName);
        if (imageUrl != null) {
            return new ImageIcon(imageUrl);
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LMSApp::new);
    }
}