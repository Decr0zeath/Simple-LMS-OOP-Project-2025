package dashboard;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

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

            JPanel assignmentPanel = new JPanel();
            assignmentPanel.setBackground(Color.CYAN);
            assignmentPanel.add(new JButton("Assignment Panel"));

            JPanel gradePanel = new JPanel();
            gradePanel.setBackground(Color.YELLOW);
            gradePanel.add(new JButton("Grade Panel"));

            // Add to CardLayout
            cardPanel.add(coursePanel, "Courses");
            cardPanel.add(assignmentPanel, "To-Do");
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
            String[] panelNames = { "Courses", "Assignments", "Grades" };

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

            // Profile dropdown menu (250px wide)
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

            // Add dummy actions
            viewProfile.addActionListener(e -> JOptionPane.showMessageDialog(LMS, "Viewing Profile..."));
            logout.addActionListener(e -> JOptionPane.showMessageDialog(LMS, "Logging out..."));

            // Show profile menu inside the frame
            pButton.addActionListener(e -> {
                Point buttonScreenPos = pButton.getLocationOnScreen();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int menuHeight = profileMenu.getPreferredSize().height;

                // Show below unless it would go off screen
                if (buttonScreenPos.y + pButton.getHeight() + menuHeight < screenSize.height) {
                    profileMenu.show(pButton, 0, pButton.getHeight());
                } else {
                    profileMenu.show(pButton, 0, -menuHeight);
                }
            });

            // Add everything to the frame
            LMS.add(headerPanel);
            LMS.add(cardPanel);
            cardPanel.setBounds(20, 120, 940, 550);
            cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            LMS.setLocationRelativeTo(null);
            LMS.setIconImage(logoIcon != null ? logoIcon.getImage() : null);
            LMS.setVisible(true);
        });
    }

    private static void styleFlatButton(JButton button, ImageIcon defaultIcon, ImageIcon hoverIcon) {
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.BLACK);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.WHITE);
                if (hoverIcon != null) button.setIcon(hoverIcon);
            }

            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.BLACK);
                if (defaultIcon != null) button.setIcon(defaultIcon);
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
