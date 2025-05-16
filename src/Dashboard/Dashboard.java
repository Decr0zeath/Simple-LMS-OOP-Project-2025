package dashboard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;

public class Dashboard {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame LMS = new JFrame("SCS Main Campus LMS");
            LMS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            LMS.setSize(1000, 730);
            LMS.setLayout(null);

            ImageIcon logoIcon = loadIcon("/resources/logo.png");

            JPanel cardPanel = new JPanel(new CardLayout());

            JPanel coursePanel = new JPanel();
            coursePanel.setBackground(Color.LIGHT_GRAY);
            coursePanel.add(new JButton("Course Panel"));

            JPanel toDoPanel = new JPanel();
            toDoPanel.setBackground(Color.CYAN);
            toDoPanel.add(new JButton("To-Do Panel"));

            JPanel gradePanel = new JPanel();
            gradePanel.setBackground(Color.YELLOW);
            gradePanel.add(new JButton("Grade Panel"));

            cardPanel.add(coursePanel, "Courses");
            cardPanel.add(toDoPanel, "To-Do");
            cardPanel.add(gradePanel, "Grades");

            // Load and resize icons
            ImageIcon courseIcon = resizeIcon(loadIcon("/resources/courses.png"));
            ImageIcon courseHoverIcon = resizeIcon(loadIcon("/resources/courses_white.png"));
            ImageIcon toDoIcon = resizeIcon(loadIcon("/resources/assignment.png"));
            ImageIcon toDoHoverIcon = resizeIcon(loadIcon("/resources/assignment_white.png"));
            ImageIcon gradeIcon = resizeIcon(loadIcon("/resources/grade.png"));
            ImageIcon gradeHoverIcon = resizeIcon(loadIcon("/resources/grade_white.png"));
            ImageIcon profileIcon = resizeIcon(loadIcon("/resources/profile.png"));
            ImageIcon profileHoverIcon = resizeIcon(loadIcon("/resources/profile_white.png"));

            int buttonWidth = 250;
            int buttonHeight = 100;

            JButton cButton = new JButton("Courses", courseIcon);
            JButton tButton = new JButton("To-Do", toDoIcon);
            JButton gButton = new JButton("Grades", gradeIcon);
            JButton pButton = new JButton("Profile", profileIcon);

            JButton[] buttons = { cButton, tButton, gButton, pButton };
            ImageIcon[] defaultIcons = { courseIcon, toDoIcon, gradeIcon, profileIcon };
            ImageIcon[] hoverIcons = { courseHoverIcon, toDoHoverIcon, gradeHoverIcon, profileHoverIcon };
            String[] panelNames = { "Courses", "To-Do", "Grades" };

            JPanel headerPanel = new JPanel(null);
            headerPanel.setBackground(new Color(0, 128, 0));
            headerPanel.setBounds(0, 0, 1000, buttonHeight);

            for (int i = 0; i < buttons.length; i++) {
                JButton btn = buttons[i];
                btn.setBounds(i * buttonWidth, 0, buttonWidth, buttonHeight);
                btn.setHorizontalTextPosition(SwingConstants.CENTER);
                btn.setVerticalTextPosition(SwingConstants.BOTTOM);
                styleFlatButton(btn, defaultIcons[i], hoverIcons[i]);
                if (i < 3) {
                    final String name = panelNames[i];
                    btn.addActionListener(e -> showPanel(cardPanel, name));
                }
                headerPanel.add(btn);
            }

            // Create the profile menu (JPopupMenu)
            JPopupMenu profileMenu = new JPopupMenu();
            profileMenu.setLayout(new BoxLayout(profileMenu, BoxLayout.Y_AXIS));
            profileMenu.setPreferredSize(new Dimension(234, 80));

            JMenuItem viewProfile = new JMenuItem("View Profile");
            JMenuItem logout = new JMenuItem("Logout");

            Dimension menuItemSize = new Dimension(buttonWidth, 40);
            viewProfile.setPreferredSize(menuItemSize);
            logout.setPreferredSize(menuItemSize);

            profileMenu.add(viewProfile);
            profileMenu.add(logout);

            // Boolean array to track if the menu is visible (moved here to fix the error)
            boolean[] isMenuVisible = { false };

            // Action listeners for the menu items
            viewProfile.addActionListener(e -> {
                openProfileFrame();
                profileMenu.setVisible(false);
                isMenuVisible[0] = false;
            });

            logout.addActionListener(e -> {
                openLogoutFrame();
                profileMenu.setVisible(false);
                isMenuVisible[0] = false;
            });

            // Profile button action listener to toggle menu visibility
            pButton.addActionListener(e -> {
                if (isMenuVisible[0]) {
                    profileMenu.setVisible(false);
                    isMenuVisible[0] = false;
                } else {
                    Point buttonScreenPos = pButton.getLocationOnScreen();
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int menuHeight = profileMenu.getPreferredSize().height;

                    if (buttonScreenPos.y + pButton.getHeight() + menuHeight < screenSize.height) {
                        profileMenu.show(pButton, 0, pButton.getHeight());
                    } else {
                        profileMenu.show(pButton, 0, -menuHeight);
                    }

                    isMenuVisible[0] = true;
                }
            });

            LMS.add(headerPanel);
            LMS.add(cardPanel);
            cardPanel.setBounds(20, 120, 940, 550);
            cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            LMS.setLocationRelativeTo(null);
            LMS.setIconImage(logoIcon != null ? logoIcon.getImage() : null);
            LMS.setVisible(true);
        });
    }

    private static void openProfileFrame() {
        JFrame profileFrame = new JFrame("Profile");
        profileFrame.setSize(300, 200);
        profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profileFrame.setLayout(new BorderLayout());

        JLabel profileLabel = new JLabel("<html><h3>John Doe's Profile</h3><p>Email: johndoe@example.com</p></html>");
        profileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profileFrame.add(profileLabel, BorderLayout.CENTER);

        profileFrame.setLocationRelativeTo(null);
        profileFrame.setVisible(true);
    }

    private static void openLogoutFrame() {
        JFrame logoutFrame = new JFrame("Logout");
        logoutFrame.setSize(300, 200);
        logoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        logoutFrame.setLayout(new BorderLayout());

        JLabel logoutLabel = new JLabel("<html><h3>Are you sure you want to logout?</h3></html>");
        logoutLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        yesButton.addActionListener(e -> {
            logoutFrame.dispose();
        });

        noButton.addActionListener(e -> logoutFrame.dispose());

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        logoutFrame.add(logoutLabel, BorderLayout.CENTER);
        logoutFrame.add(buttonPanel, BorderLayout.SOUTH);

        logoutFrame.setLocationRelativeTo(null);
        logoutFrame.setVisible(true);
    }

    private static void styleFlatButton(JButton button, ImageIcon defaultIcon, ImageIcon hoverIcon) {
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.setRequestFocusEnabled(false);
        button.setOpaque(true);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.BLACK);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(null);

        Color pressedColor = new Color(0, 100, 0);

        button.setRolloverEnabled(true);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.WHITE);
                if (hoverIcon != null) button.setIcon(hoverIcon);
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }

            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.BLACK);
                if (defaultIcon != null) button.setIcon(defaultIcon);
                button.setBorder(null);
                button.setBackground(new Color(0, 128, 0));
            }

            public void mousePressed(MouseEvent e) {
                button.setBackground(pressedColor);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            }

            public void mouseReleased(MouseEvent e) {
                button.setBackground(new Color(0, 128, 0));
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            }
        });

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                super.paint(g, c);
            }
        });
    }

    private static void showPanel(JPanel cardPanel, String panelName) {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, panelName);
    }

    private static ImageIcon loadIcon(String path) {
        URL resource = Dashboard.class.getResource(path);
        if (resource == null) {
            System.err.println("Resource not found: " + path);
            return null;
        }
        return new ImageIcon(resource);
    }

    private static ImageIcon resizeIcon(ImageIcon icon) {
        if (icon == null) return null;
        Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}
