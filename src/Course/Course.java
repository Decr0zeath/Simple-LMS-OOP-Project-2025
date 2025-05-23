package Course;

public class Course {
    private String courseId;
    private String courseName;
    private String teacher;
    private List<String> enrolledStudents;

    public Course(String courseId, String courseName, String teacher) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacher = teacher;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent(String studentName) {
        enrolledStudents.add(studentName);
    }

    public void removeStudent(String studentName) {
        enrolledStudents.remove(studentName);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId = '" + courseId + '\'' +
                ", courseName = '" + courseName + '\'' +
                ", teacher = '" + teacher + '\'' +
                ", enrolled Students = " + enrolledStudents +
                '}';
    }
}