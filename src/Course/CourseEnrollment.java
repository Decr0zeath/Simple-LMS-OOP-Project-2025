import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.color.*;

import Course.Course;
import StudentTeacher.Student;

interface CourseRepository {
    List<Course> getAllCourses();
    Course findCourseById(String courseId);
}

class InMemoryCourseRepository implements CourseRepository {
    private List<Course> courses;

    public InMemoryCourseRepository(List<Course> initialCourses) {
        this.courses = new ArrayList<>(initialCourses);
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }

    @Override
    public Course findCourseById(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equalsIgnoreCase(courseId)) {
                return course;
            }
        }
        return null;
    }
}

interface EnrollmentService {
    boolean enrollStudentInCourse(Student student, String courseCode);
    boolean unenrollStudentFromCourse(Student student, String courseCode);
    List<Course> getEnrolledCourses(Student student);
}

class StandardEnrollmentService implements EnrollmentService {
    private CourseRepository courseRepository;

    public StandardEnrollmentService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean enrollStudentInCourse(Student student, String courseId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course != null && course.canEnroll() && !student.getEnrolledCourses().contains(course)) {
            student.enroll(course);
            course.incrementEnrolledCount();
            return true;
        }
        return false;
    }

    @Override
    public boolean unenrollStudentFromCourse(Student student, String courseId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course != null && student.getEnrolledCourses().contains(course)) {
            student.unenroll(course);
            course.decrementEnrolledCount();
            return true;
        }
        return false;
    }

    @Override
    public List<Course> getEnrolledCourses(Student student) {
        return student.getEnrolledCourses();
    }
}

//GUI with Tables
class EnrollmentGUI extends JFrame implements ActionListener {
    private Student currentStudent;
    private CourseRepository courseRepository;
    private EnrollmentService enrollmentService;
    private JTable availableCoursesTable;
    private JTable enrolledCoursesTable;
    private DefaultTableModel availableCoursesModel;
    private DefaultTableModel enrolledCoursesModel;
    private JButton enrollButton;
    private JButton unenrollButton;

    public EnrollmentGUI(Student student, CourseRepository courseRepository, EnrollmentService enrollmentService) {
        this.currentStudent = student;
        this.courseRepository = courseRepository;
        this.enrollmentService = enrollmentService;

        setTitle("Course Enrollment System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Main BorderLayout with gaps

        // Padding for the main content pane
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15)); // Add some padding around the edges
        // Set the background color here
        contentPane.setBackground(new Color(69, 136, 70)); // RGB: 45, 136, 70 (Dark Green)
        add(contentPane);

        // Center Panel for Tables and Buttons
        JPanel centerPanel = new JPanel(new GridBagLayout()); // Using GridBagLayout for flexibility
        centerPanel.setOpaque(false); // Make centerPanel non-opaque so contentPane's color shows through
        contentPane.add(centerPanel, BorderLayout.CENTER);

        // Constraints for GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Available Courses Table
        availableCoursesModel = new DefaultTableModel(new Object[]{"ID", "Name", "Schedule"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        availableCoursesTable = new JTable(availableCoursesModel);
        availableCoursesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        availableCoursesTable.getTableHeader().setReorderingAllowed(false); // Prevent column reordering
        JScrollPane availableScrollPane = new JScrollPane(availableCoursesTable);
        availableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Available Courses", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 14)));
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(availableScrollPane, gbc);

        // Buttons Panel (Vertical Arrangement)
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // Make buttonPanel non-opaque
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.fill = GridBagConstraints.HORIZONTAL;
        buttonGbc.insets = new Insets(10, 5, 10, 5);
        buttonGbc.weightx = 1.0;
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonPanel.add(enrollButton = new JButton("Enroll"), buttonGbc);
        buttonGbc.gridy = 1;
        buttonPanel.add(unenrollButton = new JButton("Unenroll"), buttonGbc);

        // Empty panel to take up vertical space for button alignment
        buttonGbc.weighty = 1.0;
        buttonGbc.gridy = 2;
        
        // Gets rid of random white line
        JPanel spacerPanel = new JPanel();
        spacerPanel.setOpaque(false); // Set the spacer panel to be non-opaque
        buttonPanel.add(spacerPanel, buttonGbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.2; // Adjust width for buttons
        centerPanel.add(buttonPanel, gbc);

        // Enrolled Courses Table
        enrolledCoursesModel = new DefaultTableModel(new Object[]{"ID", "Name", "Schedule"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        enrolledCoursesTable = new JTable(enrolledCoursesModel);
        enrolledCoursesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        enrolledCoursesTable.getTableHeader().setReorderingAllowed(false); // Prevent column reordering
        JScrollPane enrolledScrollPane = new JScrollPane(enrolledCoursesTable);
        enrolledScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Enrolled Courses", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 14)));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        centerPanel.add(enrolledScrollPane, gbc);

        // Button Action Listeners
        enrollButton.addActionListener(this);
        unenrollButton.addActionListener(this);

        // Initial data loading
        loadAvailableCourses();
        loadEnrolledCourses();

        // Ensure the window size is appropriate and resizes nicely
        setPreferredSize(new Dimension(900, 600)); // Set a preferred size
        pack(); // Size the frame to fit its components
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    private void loadAvailableCourses() {
        availableCoursesModel.setRowCount(0); // Clear existing rows
        List<Course> allCourses = courseRepository.getAllCourses();
        List<Course> enrolled = enrollmentService.getEnrolledCourses(currentStudent);
        for (Course course : allCourses) {
            if (!enrolled.contains(course) && course.canEnroll()) {
                availableCoursesModel.addRow(new Object[]{
                        course.getCourseId(),
                        course.getCourseName(),
                        course.getSchedule()
                });
            }
        }
    }

    private void loadEnrolledCourses() {
        enrolledCoursesModel.setRowCount(0); // Clear existing rows
        for (Course course : enrollmentService.getEnrolledCourses(currentStudent)) {
            enrolledCoursesModel.addRow(new Object[]{
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getSchedule()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enrollButton) {
            int selectedRow = availableCoursesTable.getSelectedRow();
            if (selectedRow >= 0) {
                String courseId = (String) availableCoursesModel.getValueAt(selectedRow, 0);
                Course selectedCourse = courseRepository.findCourseById(courseId);
                if (selectedCourse != null) {
                    if (enrollmentService.enrollStudentInCourse(currentStudent, courseId)) {
                        loadAvailableCourses();
                        loadEnrolledCourses();
                        JOptionPane.showMessageDialog(this, "Enrolled in " + selectedCourse.getCourseName());
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to enroll in " + selectedCourse.getCourseName() + ". Course may be full or already enrolled.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a course to enroll in from the Available Courses table.");
            }
        } else if (e.getSource() == unenrollButton) {
            int selectedRow = enrolledCoursesTable.getSelectedRow();
            if (selectedRow >= 0) {
                String courseId = (String) enrolledCoursesModel.getValueAt(selectedRow, 0);
                Course selectedCourse = courseRepository.findCourseById(courseId);
                if (selectedCourse != null) {
                    if (enrollmentService.unenrollStudentFromCourse(currentStudent, courseId)) {
                        loadAvailableCourses();
                        loadEnrolledCourses();
                        JOptionPane.showMessageDialog(this, "Unenrolled from " + selectedCourse.getCourseName());
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to unenroll from " + selectedCourse.getCourseName() + ".");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a course to unenroll from the Enrolled Courses table.");
            }
        }
    }
}
 

// Main Class
public class CourseEnrollment {
    public static void CourseEnroll() {
        // Assume student login is already done and we have the current student object for testing
        Student loggedInStudent = new Student(01, "Sample");

        // Create some initial courses
        List<Course> initialCourses = new ArrayList<>();
        initialCourses.add(new Course("C1", "Placeholder", 30, "Mon/Wed 9:00 - 10:30"));
        initialCourses.add(new Course("C2", "Placeholder", 25, "Tue/Thu 10:30 - 12:00"));
        initialCourses.add(new Course("C3", "Placeholder", 35, "Mon/Wed 14:00 - 15:30"));
        initialCourses.add(new Course("C4", "Placeholder", 28, "Tue/Thu 15:30 - 17:00"));
        initialCourses.add(new Course("C5", "Placeholder", 30, "Tue/Thu 13:00 - 14:30"));

        
        CourseRepository courseRepository = new InMemoryCourseRepository(initialCourses);
        EnrollmentService enrollmentService = new StandardEnrollmentService(courseRepository);

          
        SwingUtilities.invokeLater(() -> new EnrollmentGUI(loggedInStudent, courseRepository, enrollmentService));
    }
}