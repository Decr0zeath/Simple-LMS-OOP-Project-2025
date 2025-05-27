import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CourseManagementGUI {
	
	public static void main(String[] args) {
		
        SwingUtilities.invokeLater(() -> {
        	// Frame
            JFrame LMS = new JFrame("SCS Main Campus LMS");
            LMS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            LMS.setSize(1000, 730);
            LMS.setLayout(null);
			LMS.setBackground(Color.lightGray); 
            
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
           
            // Card Panel
			JPanel cardPanel = new JPanel(null); 
			cardPanel.setBounds(20, 120, 940, 550);
			cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			cardPanel.setBackground(Color.WHITE); 

			// Course Info
			String courseID1 = "SCS101";
			String courseID2 = "SCS102";
			String courseID3 = "SCS103";
			String courseID4 = "SCS104";
			String courseID5 = "SCS105";

			String courseName1 = "Programming 1";
			String courseName2 = "Web Development";
			String courseName3 = "Digital Visual Arts";
			String courseName4 = "Introduction to Cyber Security";
			String courseName5 = "Data Structures and Algorithms";

			String teacherInfo1 = "John Smith";
			String teacherInfo2 = "Jane Smith";
			String teacherInfo3 = "Alice Johnson";

			// Dashboard (Course Subject Button)
				//Course Subject 1 (Prog 1)
				JButton courseSubject1 = new JButton(
					"<html>"
					+ "<div style='text-align: left; padding-left: 10px; padding-bottom: 10px;'>"
					+ "<b>" + courseID1 + "</b><br>"
					+ courseName1 + "<br>"
					+ teacherInfo1 + " & " + teacherInfo2
					+ "</div>"
					+ "</html>"
				);

				courseSubject1.setBounds(23, 25, 250, 150); 
				courseSubject1.setBorder(new javax.swing.border.EmptyBorder(15, 20, 15, 20));
				courseSubject1.setHorizontalAlignment(SwingConstants.LEFT);
				courseSubject1.setVerticalAlignment(SwingConstants.BOTTOM);
				Color skyBlue = new Color(0x87CEEB); 
				styleCourseSubject(courseSubject1, skyBlue);

				JButton courseSubject1HOME = new JButton("Home");
				courseSubject1HOME.setBounds(775, 50, 70, 50);
				styleCourseButtons(courseSubject1HOME);

				JButton courseSubject1Assignment = new JButton("Assignments");
				courseSubject1Assignment.setBounds(775, 75, 110, 50);
				styleCourseButtons(courseSubject1Assignment);

				JButton courseSubject1Grades = new JButton("Grades");
				courseSubject1Grades.setBounds(775, 100, 75, 50);
				styleCourseButtons(courseSubject1Grades);

				courseSubject1.addActionListener(e -> {
					courseSubject1.setVisible(false);


					String introMsgSubject1 ="<html>"
						+ "<div style='padding: 10px; text-align: left;'>"
						+ "<h2 style='margin: 0;'>" + courseID1 + " - " + courseName1 + "</h2>"
						+ "<p style='margin: 5px 0;'><b>Instructor:</b> " + teacherInfo1 + " & " + teacherInfo2 + "</p>"
						+ "<p style='margin: 5px 0;'>Welcome to <b>" + courseName1 + "</b>! This course will introduce you to the fundamentals of computer <br>programming, covering concepts such as algorithms, variables, loops, and functions.</p>"
						+ "<p style='margin: 5px 0;'>Throughout the semester, you’ll work on hands-on projects and exercises to build your coding skills<br> and logical thinking.</p>"
						+ "<p style='margin: 5px 0;'><i>Let’s begin your journey into the world of programming!</i></p>"
						+ "</div>"
						+ "</html>";

					JLabel introLabel = new JLabel(introMsgSubject1);
					introLabel.setVerticalAlignment(SwingConstants.TOP);
					introLabel.setBounds(25, 25, 700, 500);
					introLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
					introLabel.setOpaque(true);
					introLabel.setBackground(Color.white); 

					cardPanel.removeAll();
					cardPanel.add(introLabel);
					cardPanel.add(courseSubject1HOME); 
					cardPanel.add(courseSubject1Assignment);
					cardPanel.add(courseSubject1Grades);
					courseSubject1HOME.setVisible(true);
					courseSubject1Assignment.setVisible(true);
					courseSubject1Grades.setVisible(true);
					cardPanel.revalidate();
					cardPanel.repaint();
				});


			//Course Subject 2 (Web Dev)
				JButton courseSubject2 = new JButton(
					"<html>"
					+ "<div style='text-align: left; padding-left: 10px; padding-bottom: 10px;'>"
					+ "<b>" + courseID2 + "</b><br>"
					+ courseName2 + "<br>"
					+ teacherInfo2 + " & " + teacherInfo3
					+ "</div>"
					+ "</html>"
				);

				courseSubject2.setBounds(345, 25, 250, 150); 
				courseSubject2.setBorder(new javax.swing.border.EmptyBorder(15, 20, 15, 20));
				courseSubject2.setHorizontalAlignment(SwingConstants.LEFT);
				courseSubject2.setVerticalAlignment(SwingConstants.BOTTOM);
				Color lightLimeGreen = new Color(0xEAF6B0);
				styleCourseSubject(courseSubject2, lightLimeGreen);

				JButton courseSubject2HOME = new JButton("Home");
				courseSubject2HOME.setBounds(775, 50, 70, 50);
				styleCourseButtons(courseSubject2HOME);

				JButton courseSubject2Assignment = new JButton("Assignments");
				courseSubject2Assignment.setBounds(775, 75, 110, 50);
				styleCourseButtons(courseSubject2Assignment);

				JButton courseSubject2Grades = new JButton("Grades");
				courseSubject2Grades.setBounds(775, 100, 75, 50);
				styleCourseButtons(courseSubject2Grades);

				courseSubject2.addActionListener(e -> {
					courseSubject1.setVisible(false);
					courseSubject2.setVisible(false);

					String introMsgSubject2 = "<html>"
						+ "<div style='padding: 10px; text-align: left;'>"
						+ "<h2 style='margin: 0;'>" + courseID2 + " - " + courseName2 + "</h2>"
						+ "<p style='margin: 5px 0;'><b>Instructor:</b> " + teacherInfo2 + " & " + teacherInfo3 + "</p>"
						+ "<p style='margin: 5px 0;'>Welcome to <b>" + courseName2 + "</b>! In this course, you'll explore the core principles of web development,<br>"
						+ "learning how to build interactive and visually appealing websites using HTML, CSS, and JavaScript.</p>"
						+ "<p style='margin: 5px 0;'>Throughout the semester, you’ll design responsive layouts, implement dynamic functionality,<br>"
						+ "and gain hands-on experience by building real-world web projects.</p>"
						+ "<p style='margin: 5px 0;'><i>Let’s start creating the web — one line of code at a time!</i></p>"
						+ "</div>"
						+ "</html>";

					JLabel introLabel = new JLabel(introMsgSubject2);
					introLabel.setVerticalAlignment(SwingConstants.TOP);
					introLabel.setBounds(25, 25, 700, 500);
					introLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
					introLabel.setOpaque(true);
					introLabel.setBackground(Color.white); 

					cardPanel.removeAll();
					cardPanel.add(introLabel);
					cardPanel.add(courseSubject2HOME); 
					cardPanel.add(courseSubject2Assignment);
					cardPanel.add(courseSubject2Grades);
					courseSubject2HOME.setVisible(true);
					courseSubject2Assignment.setVisible(true);
					courseSubject2Grades.setVisible(true);
					cardPanel.revalidate();
					cardPanel.repaint();
				});

				//Course Subject 3 (Digital Visual Arts)
				JButton courseSubject3 = new JButton(
					"<html>"
					+ "<div style='text-align: left; padding-left: 10px; padding-bottom: 10px;'>"
					+ "<b>" + courseID3 + "</b><br>"
					+ courseName3 + "<br>"
					+ teacherInfo1 + " & " + teacherInfo3
					+ "</div>"
					+ "</html>"
				);

				courseSubject3.setBounds(667, 25, 250, 150); 
				courseSubject3.setBorder(new javax.swing.border.EmptyBorder(15, 20, 15, 20));
				courseSubject3.setHorizontalAlignment(SwingConstants.LEFT);
				courseSubject3.setVerticalAlignment(SwingConstants.BOTTOM);
				Color lightLavenderPink = new Color(0xEDD0F7);
				styleCourseSubject(courseSubject3, lightLavenderPink);

				JButton courseSubject3HOME = new JButton("Home");
				courseSubject3HOME.setBounds(775, 50, 70, 50);
				styleCourseButtons(courseSubject3HOME);

				JButton courseSubject3Assignment = new JButton("Assignments");
				courseSubject3Assignment.setBounds(775, 75, 110, 50);
				styleCourseButtons(courseSubject3Assignment);

				JButton courseSubject3Grades = new JButton("Grades");
				courseSubject3Grades.setBounds(775, 100, 75, 50);
				styleCourseButtons(courseSubject3Grades);

				courseSubject3.addActionListener(e -> {
					courseSubject1.setVisible(false);
					courseSubject2.setVisible(false);
					courseSubject3.setVisible(false);


					String introMsgSubject3 = "<html>"
						+ "<div style='padding: 10px; text-align: left;'>"
						+ "<h2 style='margin: 0;'>" + courseID3 + " - " + courseName3 + "</h2>"
						+ "<p style='margin: 5px 0;'><b>Instructor:</b> " + teacherInfo1 + " & " + teacherInfo3 + "</p>"
						+ "<p style='margin: 5px 0;'>Welcome to <b>" + courseName3 + "</b>! This course introduces you to the creative world of digital visual arts,<br>"
						+ "where you'll learn to express ideas through digital illustration, graphic design, and visual storytelling.</p>"
						+ "<p style='margin: 5px 0;'>Throughout the semester, you’ll explore design principles, color theory, composition,<br>"
						+ "and gain hands-on experience using digital tools and software to bring your artistic vision to life.</p>"
						+ "<p style='margin: 5px 0;'><i>Get ready to create, inspire, and communicate through digital art!</i></p>"
						+ "</div>"
						+ "</html>";

					JLabel introLabel = new JLabel(introMsgSubject3);
					introLabel.setVerticalAlignment(SwingConstants.TOP);
					introLabel.setBounds(25, 25, 700, 500);
					introLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
					introLabel.setOpaque(true);
					introLabel.setBackground(Color.white); 

					cardPanel.removeAll();
					cardPanel.add(introLabel);
					cardPanel.add(courseSubject3HOME); 
					cardPanel.add(courseSubject3Assignment);
					cardPanel.add(courseSubject3Grades);
					courseSubject3HOME.setVisible(true);
					courseSubject3Assignment.setVisible(true);
					courseSubject3Grades.setVisible(true);
					cardPanel.revalidate();
					cardPanel.repaint();
				});

				//Course Subject 4 (Cyber Security)
				JButton courseSubject4 = new JButton(
					"<html>"
					+ "<div style='text-align: left; padding-left: 10px; padding-bottom: 10px;'>"
					+ "<b>" + courseID4 + "</b><br>"
					+ courseName4 + "<br>"
					+ teacherInfo3
					+ "</div>"
					+ "</html>"
				);

				courseSubject4.setBounds(23, 200, 250, 150); 
				courseSubject4.setBorder(new javax.swing.border.EmptyBorder(15, 20, 15, 20));
				courseSubject4.setHorizontalAlignment(SwingConstants.LEFT);
				courseSubject4.setVerticalAlignment(SwingConstants.BOTTOM);
				Color lightLimeGreeny = new Color(0xD6F2D5);
				styleCourseSubject(courseSubject4, lightLimeGreeny);

				JButton courseSubject4HOME = new JButton("Home");
				courseSubject4HOME.setBounds(775, 50, 70, 50);
				styleCourseButtons(courseSubject4HOME);

				JButton courseSubject4Assignment = new JButton("Assignments");
				courseSubject4Assignment.setBounds(775, 75, 110, 50);
				styleCourseButtons(courseSubject4Assignment);

				JButton courseSubject4Grades = new JButton("Grades");
				courseSubject4Grades.setBounds(775, 100, 75, 50);
				styleCourseButtons(courseSubject4Grades);

				courseSubject4.addActionListener(e -> {
					courseSubject1.setVisible(false);
					courseSubject2.setVisible(false);
					courseSubject3.setVisible(false);
					courseSubject4.setVisible(false);

					String introMsgSubject4 = "<html>"
						+ "<div style='padding: 10px; text-align: left;'>"
						+ "<h2 style='margin: 0;'>" + courseID4 + " - " + courseName4 + "</h2>"
						+ "<p style='margin: 5px 0;'><b>Instructor:</b> " + teacherInfo3 + "</p>"
						+ "<p style='margin: 5px 0;'>Welcome to <b>" + courseName4 + "</b>! In this course, you’ll delve into the fundamentals of cybersecurity,<br>"
						+ "exploring how to protect systems, networks, and data from digital threats and vulnerabilities.</p>"
						+ "<p style='margin: 5px 0;'>Throughout the semester, you’ll learn about risk assessment, encryption, ethical hacking,<br>"
						+ "and best practices in securing information in an increasingly connected world.</p>"
						+ "<p style='margin: 5px 0;'><i>Let’s take the first step in defending the digital frontier!</i></p>"
						+ "</div>"
						+ "</html>";

					JLabel introLabel = new JLabel(introMsgSubject4);
					introLabel.setVerticalAlignment(SwingConstants.TOP);
					introLabel.setBounds(25, 25, 700, 500);
					introLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
					introLabel.setOpaque(true);
					introLabel.setBackground(Color.white); 

					cardPanel.removeAll();
					cardPanel.add(introLabel);
					cardPanel.add(courseSubject4HOME); 
					cardPanel.add(courseSubject4Assignment);
					cardPanel.add(courseSubject4Grades);
					courseSubject4HOME.setVisible(true);
					courseSubject4Assignment.setVisible(true);
					courseSubject4Grades.setVisible(true);
					cardPanel.revalidate();
					cardPanel.repaint();
				});

				//Course Subject 5 (Data Structures & Algo)
				JButton courseSubject5 = new JButton(
					"<html>"
					+ "<div style='text-align: left; padding-left: 10px; padding-bottom: 10px;'>"
					+ "<b>" + courseID5 + "</b><br>"
					+ courseName5 + "<br>"
					+ teacherInfo1 + " & " + teacherInfo2
					+ "</div>"
					+ "</html>"
				);

				courseSubject5.setBounds(345, 200, 250, 150); 
				courseSubject5.setBorder(new javax.swing.border.EmptyBorder(15, 20, 15, 20));
				courseSubject5.setHorizontalAlignment(SwingConstants.LEFT);
				courseSubject5.setVerticalAlignment(SwingConstants.BOTTOM);
				Color pinkHeart = new Color(0xFACAC8);
				styleCourseSubject(courseSubject5, pinkHeart);

				JButton courseSubject5HOME = new JButton("Home");
				courseSubject5HOME.setBounds(775, 50, 70, 50);
				styleCourseButtons(courseSubject5HOME);

				JButton courseSubject5Assignment = new JButton("Assignments");
				courseSubject5Assignment.setBounds(775, 75, 110, 50);
				styleCourseButtons(courseSubject5Assignment);

				JButton courseSubject5Grades = new JButton("Grades");
				courseSubject5Grades.setBounds(775, 100, 75, 50);
				styleCourseButtons(courseSubject5Grades);

				courseSubject5.addActionListener(e -> {
					courseSubject1.setVisible(false);
					courseSubject2.setVisible(false);
					courseSubject3.setVisible(false);
					courseSubject4.setVisible(false);
					courseSubject5.setVisible(false);

					String introMsgSubject5 = "<html>"
						+ "<div style='padding: 10px; text-align: left;'>"
						+ "<h2 style='margin: 0;'>" + courseID5 + " - " + courseName5 + "</h2>"
						+ "<p style='margin: 5px 0;'><b>Instructor:</b> " + teacherInfo1 + " & " + teacherInfo2 + "</p>"
						+ "<p style='margin: 5px 0;'>Welcome to <b>" + courseName5 + "</b>! In this course, you’ll explore the essential concepts of data structures and algorithms,<br>"
						+ "learning how to organize data efficiently and solve complex problems with optimized solutions.</p>"
						+ "<p style='margin: 5px 0;'>Throughout the semester, you’ll implement and analyze various data structures like arrays, linked lists, trees, and graphs,<br>"
						+ "and master algorithms for sorting, searching, and problem-solving techniques.</p>"
						+ "<p style='margin: 5px 0;'><i>Get ready to sharpen your coding skills and think algorithmically!</i></p>"
						+ "</div>"
						+ "</html>";

					JLabel introLabel = new JLabel(introMsgSubject5);
					introLabel.setVerticalAlignment(SwingConstants.TOP);
					introLabel.setBounds(25, 25, 700, 500);
					introLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 16));
					introLabel.setOpaque(true);
					introLabel.setBackground(Color.white); 

					cardPanel.removeAll();
					cardPanel.add(introLabel);
					cardPanel.add(courseSubject5HOME); 
					cardPanel.add(courseSubject5Assignment);
					cardPanel.add(courseSubject5Grades);
					courseSubject5HOME.setVisible(true);
					courseSubject5Assignment.setVisible(true);
					courseSubject5Grades.setVisible(true);
					cardPanel.revalidate();
					cardPanel.repaint();
				});

			viewCourseButton.addActionListener(e -> {
				cardPanel.removeAll();
				courseSubject1.setBorder(new LineBorder(Color.BLACK, 2)); // 1
				courseSubject1.setBackground(new Color(0x87CEEB)); 

				courseSubject2.setBorder(new LineBorder(Color.BLACK, 2)); // 2
				courseSubject2.setBackground(new Color(0xEAF6B0)); 

				courseSubject3.setBorder(new LineBorder(Color.BLACK, 2)); // 3
				courseSubject3.setBackground(new Color(0xEDD0F7)); 

				courseSubject4.setBorder(new LineBorder(Color.BLACK, 2)); // 4
				courseSubject4.setBackground(new Color(0xD6F2D5)); 

				courseSubject5.setBorder(new LineBorder(Color.BLACK, 2)); // 5
				courseSubject5.setBackground(new Color(0xFACAC8)); 

				courseSubject1.setVisible(true);  // 1
				cardPanel.add(courseSubject1);
				courseSubject1.revalidate();
				courseSubject1.repaint();

				courseSubject2.setVisible(true); // 2
				cardPanel.add(courseSubject2);
				courseSubject2.revalidate();
				courseSubject2.repaint();

				courseSubject3.setVisible(true); // 3
				cardPanel.add(courseSubject3);
				courseSubject3.revalidate();
				courseSubject3.repaint();

				courseSubject4.setVisible(true); // 4
				cardPanel.add(courseSubject4);
				courseSubject4.revalidate();
				courseSubject4.repaint();

				courseSubject5.setVisible(true); // 5
				cardPanel.add(courseSubject5);
				courseSubject5.revalidate();
				courseSubject5.repaint();

				cardPanel.revalidate();
				cardPanel.repaint();
			});

            // Add to frame
            LMS.setLocationRelativeTo(null);
            LMS.setVisible(true);
            LMS.setIconImage(logoIcon.getImage());
			
            LMS.add(viewCourseButton);

			LMS.add(cardPanel);
            
			cardPanel.add(courseSubject1);
			cardPanel.add(courseSubject2);
			cardPanel.add(courseSubject3);
			cardPanel.add(courseSubject4);
			cardPanel.add(courseSubject5);
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

	// Buttons inside the Course
	private static void styleCourseButtons(JButton button) {
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setOpaque(false);
		button.setForeground(new Color(0, 100, 0));
		button.setVisible(false);
	}
}
