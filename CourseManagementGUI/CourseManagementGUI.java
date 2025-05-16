package CourseManagementGUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

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
           
            // Dashboard
            JButton courseSubject = new JButton(
            	    "<html>"
            	    + "<div style='text-align: left; padding-left: 10px; padding-bottom: 10px;'>"
            	    + "<b>CS101</b><br>" // Bold Course ID
            	    + "Object Oriented Programming<br>" // Normal Course Name
            	    + "Engr. Violdan Bayocot" // Normal Teacher
            	    + "</div>"
            	    + "</html>"
            	);
            courseSubject.setBounds(10, 160, 250, 150);
            courseSubject.setBorder(new javax.swing.border.EmptyBorder(15, 20, 15, 20));
            courseSubject.setHorizontalAlignment(SwingConstants.LEFT);
            courseSubject.setVerticalAlignment(SwingConstants.BOTTOM);
            Color randomColor = getRandomColor();
            styleCourseSubject(courseSubject, randomColor);


            // Add to frame
            LMS.setLocationRelativeTo(null);
            LMS.setVisible(true);
            LMS.setIconImage(logoIcon.getImage());
            
            LMS.add(viewCourseButton);
            LMS.add(viewAssignmentButton);
            LMS.add(viewGradeButton);
            
            LMS.add(courseSubject);
        
        });
    }
	
	//Random Color 
	private static Color getRandomColor() {
	    int r = (int) (Math.random() * 256);
	    int g = (int) (Math.random() * 256);
	    int b = (int) (Math.random() * 256);
	    return new Color(r, g, b);
	}
	//Customize Navigation Buttons
	private static void styleFlatButton(JButton button) {
	    button.setFocusPainted(false);
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
	private static void styleCourseSubject(JButton button, Color randomColor) {
		 button.setFocusPainted(false);
		    button.setOpaque(true);
		    button.setBackground(randomColor); 
		    button.setForeground(Color.BLACK);
		    button.setBorder(new LineBorder(Color.BLACK, 2));
		    button.addMouseListener(new MouseAdapter() {
		        public void mouseEntered(MouseEvent e) {
		            button.setBorder(new LineBorder(new Color(50, 50, 50), 4));
		            button.setBackground(randomColor.darker());
		        }

		        public void mouseExited(MouseEvent e) {
		            button.setBorder(new LineBorder(Color.BLACK, 2));
		            button.setBackground(randomColor);
		        }
		    });
	}
}
