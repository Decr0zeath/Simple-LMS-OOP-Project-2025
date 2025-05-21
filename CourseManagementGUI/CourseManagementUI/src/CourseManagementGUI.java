
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CourseManagementGUI {
	
	public static void main(String[] args) {
		
        SwingUtilities.invokeLater(() -> {
        	// Frame
            JFrame LMS = new JFrame("SCS Main Campus LMS");
            LMS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            LMS.setSize(1000, 750);
            LMS.setLayout(null);
            
            // LMS-Logo
            ImageIcon logoIcon = new ImageIcon(CourseManagementGUI.class.getResource("/resources/lms-logo.png"));

            // ===== Course Button =====
            ImageIcon courseIcon = new ImageIcon(CourseManagementGUI.class.getResource("/resources/course-logo.png"));
            Image courseImg = courseIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon resizedCourseIcon = new ImageIcon(courseImg);
            
            JButton viewCourseButton = new JButton("Courses", resizedCourseIcon);
            viewCourseButton.setBounds(10, 10, 150, 100);
            viewCourseButton.setHorizontalTextPosition(SwingConstants.CENTER); 
            viewCourseButton.setVerticalTextPosition(SwingConstants.BOTTOM); 
            styleFlatButton(viewCourseButton);

            // ===== To-Do Button =====
            ImageIcon assignmentIcon = new ImageIcon(CourseManagementGUI.class.getResource("/resources/assignment-logo.png"));
            Image assImg = assignmentIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon resizedAssignmentIcon = new ImageIcon(assImg);
            
            JButton viewAssignmentButton = new JButton("To Do", resizedAssignmentIcon);
            viewAssignmentButton.setBounds(180, 10, 150, 100);
            viewAssignmentButton.setHorizontalTextPosition(SwingConstants.CENTER); 
            viewAssignmentButton.setVerticalTextPosition(SwingConstants.BOTTOM); 
            styleFlatButton(viewAssignmentButton);

            // ===== Grade Button =====
            ImageIcon gradeIcon = new ImageIcon(CourseManagementGUI.class.getResource("/resources/grades-logo.png"));
            Image gradeImg = gradeIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon resizedGradeIcon = new ImageIcon(gradeImg);
            
            JButton viewGradeButton = new JButton("Grades", resizedGradeIcon);
            viewGradeButton.setBounds(350, 10, 150, 100);
            viewGradeButton.setHorizontalTextPosition(SwingConstants.CENTER); 
            viewGradeButton.setVerticalTextPosition(SwingConstants.BOTTOM); 
            styleFlatButton(viewGradeButton);
           
            // Card Panel
			JPanel cardPanel = new JPanel(null); 
			cardPanel.setBounds(20, 120, 940, 550);
			cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			cardPanel.setBackground(Color.LIGHT_GRAY); 

			// Dashboard (Course Subject Button)
			JButton courseSubject = new JButton(
				"<html>"
				+ "<div style='text-align: left; padding-left: 10px; padding-bottom: 10px;'>"
				+ "<b>SCS101</b><br>"  //courseID
				+ "Programming 1<br>"  //courseName
				+ "John Smith"         //TeacherInfo
				+ "</div>"
				+ "</html>"
			);
			courseSubject.setBounds(25, 25, 225, 150); 
			courseSubject.setBorder(new javax.swing.border.EmptyBorder(15, 20, 15, 20));
			courseSubject.setHorizontalAlignment(SwingConstants.LEFT);
			courseSubject.setVerticalAlignment(SwingConstants.BOTTOM);
			Color skyBlue = new Color(0x87CEEB); // Hex to RGB
			styleCourseSubject(courseSubject, skyBlue);

            // Add to frame
            LMS.setLocationRelativeTo(null);
            LMS.setVisible(true);
            LMS.setIconImage(logoIcon.getImage());
			
            LMS.add(viewCourseButton);
			LMS.add(viewAssignmentButton);
			LMS.add(viewGradeButton);
			LMS.add(cardPanel);
            
			cardPanel.add(courseSubject);
        });
    }

	//Customize Navigation Buttons
	private static void styleFlatButton(JButton button) {
	    button.setFocusPainted(false);
		button.setContentAreaFilled(false);
	    button.setOpaque(true); // Make background color visible
	    button.setBackground(new Color(0, 128, 0)); // Green background by default
	    button.setForeground(Color.BLACK); // Black text by default
	    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    button.setBorder(new LineBorder(Color.BLACK, 2)); // Black border by default

	    button.addMouseListener(new MouseAdapter() {
	        public void mouseEntered(MouseEvent e) {
	            button.setForeground(Color.WHITE); // Green text on hover
	            button.setBorder(new LineBorder(Color.WHITE, 2)); // Green border on hover
	        }

	        public void mouseExited(MouseEvent e) {
	            button.setForeground(Color.BLACK); // Back to black text
	            button.setBorder(new LineBorder(Color.BLACK, 2)); // Back to black border
	        }
	    });
	}

	//Customize Course Subject
	private static void styleCourseSubject(JButton button, Color color) {
    button.setFocusPainted(false);
    button.setFocusable(false);
    button.setRequestFocusEnabled(false);
    button.setContentAreaFilled(false); 
    button.setOpaque(true);           
    button.setBackground(color);
    button.setForeground(Color.BLACK);
    button.setBorder(new LineBorder(Color.BLACK, 2));

    button.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            button.setBorder(new LineBorder(new Color(50, 50, 50), 4));
            button.setBackground(color.darker());
        }

        public void mouseExited(MouseEvent e) {
            button.setBorder(new LineBorder(Color.BLACK, 2));
            button.setBackground(color);
        }
    });
	}
}
