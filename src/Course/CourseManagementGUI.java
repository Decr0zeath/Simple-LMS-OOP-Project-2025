package Course;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class CourseManagementGUI extends JPanel {

    private JPanel cardPanel;

    private final String[] courseIDs = {
        "SCS101", "SCS102", "SCS103", "SCS104", "SCS105"
    };

    private final String[] courseNames = {
        "Programming 1", "Web Development", "Digital Visual Arts", "Cyber Security", "Data Structures and Algorithms"
    };

    private final String[][] teachersPerCourse = {
    {"John Smith", "Jane Smith"},           
    {"Jane Smith", "Alice Johnson"},      
    {"John Smith", "Alice Johnson"},       
    {"Alice Johnson"},                      
    {"John Smith", "Jane Smith"}           
    };


    private final Color[] colors = {
        new Color(0x87CEEB),
        new Color(0xF7F6A3),
        new Color(0x90EE90),
        new Color(0xFFB6C1),
        new Color(0xFFA07A)
    };

    public CourseManagementGUI() {
        setLayout(null);
        setBackground(Color.WHITE);
        setBounds(0, 0, 940, 550);

        cardPanel = new JPanel(null);
        cardPanel.setBounds(0, 0, 940, 550);
        cardPanel.setBackground(Color.WHITE);
        add(cardPanel);

        showCourseButtons();
    }

    private JButton createCourseButton(int index) {
        String teacherText = String.join(", ", teachersPerCourse[index]);
        String text = "<html><div style='margin-left:5px; margin-top:70px;'><b>" + courseIDs[index] + "</b><br>"
        + courseNames[index] + "<br>" + teacherText + "</div></html>";

        JButton btn = new JButton(text);
        btn.setBounds(20 + (index % 3) * 310, 20 + (index / 3) * 180, 280, 160);
        styleCourseButton(btn, colors[index]);

        btn.addActionListener(e -> showCourseInfo(index));
        return btn;
    }

    private void styleCourseButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBorder(new LineBorder(Color.BLACK, 2));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setVerticalAlignment(SwingConstants.TOP);
    }

    public void showCourseButtons() {
        cardPanel.removeAll();

        for (int i = 0; i < courseIDs.length; i++) {
            JButton courseButton = createCourseButton(i);
            cardPanel.add(courseButton);
        }

        cardPanel.revalidate();
        cardPanel.repaint();
    }

    private void showCourseInfo(int index) {
        cardPanel.removeAll();

        String courseID = courseIDs[index];
        String courseName = courseNames[index];
        String teacherText = String.join(", ", teachersPerCourse[index]);

        String htmlText = "<html><h1>" + courseID + " - " + courseName + "</h1>"
        + "<p><b>Instructor(s):</b> " + teacherText + "</p>"
        + "<p><br>This is an in-depth description of the course " + courseName + ".<br> "
        + "Students will learn fundamental and advanced concepts in this course. "
        + "<br>The instructor will guide through projects, assignments, and exams.</p>"
        + "<br><p>More course details can be added here.</p></html>";


        JLabel infoLabel = new JLabel(htmlText);
        infoLabel.setBounds(20, 20, 900, 560);
        infoLabel.setVerticalAlignment(SwingConstants.TOP);
        infoLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        infoLabel.setOpaque(true);
        infoLabel.setBackground(Color.WHITE);

        cardPanel.add(infoLabel);
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    // ✅ Called from Dashboard to reset view
    public void resetToCourseButtons() {
        showCourseButtons();
    }
}
