package Course;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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

   class EnrollmentManager {
    private Map<Student, List<Course>> enrollmentMap = new HashMap<>();
    private Map<String, Integer> enrollmentCounts = new HashMap<>(); // courseId -> count

    // New: Pending enrollments (courseId -> list of students)
    private Map<String, List<Student>> pendingEnrollments = new HashMap<>();

    // Request enrollment (adds to pending)
    public boolean requestEnrollment(Student student, Course course) {
        pendingEnrollments.putIfAbsent(course.getCourseId(), new ArrayList<>());
        List<Student> pending = pendingEnrollments.get(course.getCourseId());
        if (!pending.contains(student)) {
            pending.add(student);
            return true;
        }
        return false; // already pending
    }

    // Confirm enrollment (teacher action)
    public boolean confirmEnrollment(Student student, Course course) {
        List<Student> pending = pendingEnrollments.getOrDefault(course.getCourseId(), new ArrayList<>());
        if (pending.contains(student)) {
            pending.remove(student);
            return enroll(student, course);
        }
        return false;
    }

    public List<Student> getPendingStudents(Course course) {
        return pendingEnrollments.getOrDefault(course.getCourseId(), new ArrayList<>());
    }

    public boolean enroll(Student student, Course course) {
        enrollmentMap.putIfAbsent(student, new ArrayList<>());
        List<Course> enrolledCourses = enrollmentMap.get(student);
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            // Increment count
            String courseId = course.getCourseId();
            enrollmentCounts.put(courseId, enrollmentCounts.getOrDefault(courseId, 0) + 1);
            return true;
        }
        return false; // already enrolled
    }

    public boolean unenroll(Student student, Course course) {
        List<Course> enrolledCourses = enrollmentMap.get(student);
        if (enrolledCourses != null && enrolledCourses.remove(course)) {
            // Decrement count
            String courseId = course.getCourseId();
            int count = enrollmentCounts.getOrDefault(courseId, 0);
            if (count > 0) {
                enrollmentCounts.put(courseId, count - 1);
            }
            return true;
        }
        return false;
    }

    public List<Course> getEnrolledCourses(Student student) {
        return enrollmentMap.getOrDefault(student, new ArrayList<>());
    }

    public boolean isEnrolled(Student student, Course course) {
        List<Course> enrolledCourses = enrollmentMap.get(student);
        return enrolledCourses != null && enrolledCourses.contains(course);
    }

    public int getEnrolledCount(Course course) {
        return enrollmentCounts.getOrDefault(course.getCourseId(), 0);
    }

    public boolean canEnrollInCourse(Course course) {
        int capacity = 30; // or make this configurable
        return getEnrolledCount(course) < capacity;
    }
}

interface EnrollmentService {
    boolean requestEnrollment(Student student, String courseCode); // student requests
    boolean confirmEnrollment(Student student, String courseCode); // teacher confirms
    boolean unenrollStudentFromCourse(Student student, String courseCode);
    List<Course> getEnrolledCourses(Student student);
    EnrollmentManager getEnrollmentManager();
    List<Student> getPendingStudents(String courseCode);
    // Deprecated: Only for teacher confirmation, not for student use
    @Deprecated
    boolean enrollStudentInCourse(Student student, String courseId);
}

class StandardEnrollmentService implements EnrollmentService {
    private CourseRepository courseRepository;
    private EnrollmentManager enrollmentManager;

    public StandardEnrollmentService(CourseRepository courseRepository, EnrollmentManager enrollmentManager) {
        this.courseRepository = courseRepository;
        this.enrollmentManager = enrollmentManager;
    }

    @Override
    public EnrollmentManager getEnrollmentManager() {
        return enrollmentManager;
    }

    @Override
    public boolean requestEnrollment(Student student, String courseId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course != null && !enrollmentManager.isEnrolled(student, course)) {
            return enrollmentManager.requestEnrollment(student, course);
        }
        return false;
    }

    @Override
    public boolean confirmEnrollment(Student student, String courseId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course != null) {
            return enrollmentManager.confirmEnrollment(student, course);
        }
        return false;
    }

    @Override
    public List<Student> getPendingStudents(String courseId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course != null) {
            return enrollmentManager.getPendingStudents(course);
        }
        return new ArrayList<>();
    }

    @Override
    @Deprecated
    public boolean enrollStudentInCourse(Student student, String courseId) {
        // Only use this for teacher confirmation, not for student requests!
        return confirmEnrollment(student, courseId);
    }

    @Override
    public boolean unenrollStudentFromCourse(Student student, String courseId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course != null && enrollmentManager.isEnrolled(student, course)) {
            return enrollmentManager.unenroll(student, course);
        }
        return false;
    }

    @Override
    public List<Course> getEnrolledCourses(Student student) {
        return enrollmentManager.getEnrolledCourses(student);
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
    private JLabel studentInfoLabel;

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

        // Enrolled Courses Table (LEFT)
        enrolledCoursesModel = new DefaultTableModel(new Object[]{"ID", "Name"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        enrolledCoursesTable = new JTable(enrolledCoursesModel);
        enrolledCoursesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        enrolledCoursesTable.getTableHeader().setReorderingAllowed(false);
        enrolledCoursesTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        enrolledCoursesTable.getColumnModel().getColumn(0).setMinWidth(50);
        enrolledCoursesTable.getColumnModel().getColumn(0).setMaxWidth(80);
        JScrollPane enrolledScrollPane = new JScrollPane(enrolledCoursesTable);
        enrolledScrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Enrolled Courses", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 14)));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        centerPanel.add(enrolledScrollPane, gbc);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.fill = GridBagConstraints.HORIZONTAL;
        buttonGbc.insets = new Insets(5, 5, 5, 5);
        buttonGbc.weightx = 1.0;
        //Student info label above enroll button
        studentInfoLabel = new JLabel("Student: " + currentStudent.getFirstName() + " " + currentStudent.getLastName() + " (ID: " + currentStudent.getAccountID() + ")");
        studentInfoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        studentInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        studentInfoLabel.setForeground(Color.white);
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonGbc.gridwidth = 2;
        buttonPanel.add(studentInfoLabel, buttonGbc);

        buttonGbc.gridwidth = 1;
        buttonGbc.gridy = 1;
        buttonPanel.add(enrollButton = new JButton("Enroll"), buttonGbc);
        buttonGbc.gridy = 2;
        buttonPanel.add(unenrollButton = new JButton("Unenroll"), buttonGbc);

        buttonGbc.weighty = 1.0;
        buttonGbc.gridy = 3;
        JPanel spacerPanel = new JPanel();
        spacerPanel.setOpaque(false);
        buttonPanel.add(spacerPanel, buttonGbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        centerPanel.add(buttonPanel, gbc);

        // Available Courses Table (RIGHT)
        availableCoursesModel = new DefaultTableModel(new Object[]{"ID", "Name"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        availableCoursesTable = new JTable(availableCoursesModel);
        availableCoursesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        availableCoursesTable.getTableHeader().setReorderingAllowed(false);
        availableCoursesTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        availableCoursesTable.getColumnModel().getColumn(0).setMinWidth(50);
        availableCoursesTable.getColumnModel().getColumn(0).setMaxWidth(80);
        JScrollPane availableScrollPane = new JScrollPane(availableCoursesTable);
        availableScrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Available Courses", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 14)));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        centerPanel.add(availableScrollPane, gbc);

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

        setResizable(false); // <-- Add this line to disable maximize button

        setVisible(true);
    }

    private void loadAvailableCourses() {
        availableCoursesModel.setRowCount(0); // Clear existing rows
        List<Course> allCourses = courseRepository.getAllCourses();
        List<Course> enrolled = enrollmentService.getEnrolledCourses(currentStudent);
        for (Course course : allCourses) {
            if (!enrolled.contains(course) && 
                enrollmentService.getEnrollmentManager().canEnrollInCourse(course)) { // changed here
                availableCoursesModel.addRow(new Object[]{
                        course.getCourseId(),
                        course.getCourseName(), 
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
                    // Request enrollment instead of enrolling directly
                    if (enrollmentService.requestEnrollment(currentStudent, courseId)) {
                        JOptionPane.showMessageDialog(this, "Enrollment request sent for " + selectedCourse.getCourseName() + ". Waiting for teacher confirmation.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to send enrollment request for " + selectedCourse.getCourseName() + ". You may have already requested or are enrolled.");
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

    // Now expects a CourseRepository and Student to be passed in
    public static void launchEnrollmentGUI(Student enrolledStudent, CourseRepository courseRepository) {
        EnrollmentManager enrollmentManager = new EnrollmentManager();
        EnrollmentService enrollmentService = new StandardEnrollmentService(courseRepository, enrollmentManager);

        SwingUtilities.invokeLater(() -> new EnrollmentGUI(enrolledStudent, courseRepository, enrollmentService));
    }

    // Add this main method to allow running standalone
    public static void main(String[] args) {
        // Sample student
        // Fix: The Student constructor should match your Student class definition.
        
        Student sampleStudent = new Student(" ", " ", " ", " ", " ", 1);

        // Sample courses
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("SCS1", "Programming 1"));
        courses.add(new Course("SCS2", "Web Development"));
        courses.add(new Course("SCS3", "Digital Visual Arts"));
        courses.add(new Course("SCS4", "Cyber Security"));
        courses.add(new Course("SCS5", "Data Structures"));

        CourseRepository courseRepository = new InMemoryCourseRepository(courses);

        launchEnrollmentGUI(sampleStudent, courseRepository);
    }
}

