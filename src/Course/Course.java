package Course;

import StudentTeacher.Teacher;
import StudentTeacher.student;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Course {
    private final String courseId;
    private final String courseName;
    private List<Teacher> teachers;
    private List<student> enrolledStudents;

    public Course(String courseId, String courseName, Teacher... teachers) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teachers = Arrays.asList(teachers);
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent(student student) {
        enrolledStudents.add(student);
    }

    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(courseId).append(",").append(courseName).append(", Teachers: ");
        for (int i = 0; i < teachers.size(); i++) {
            Teacher t = teachers.get(i);
            sb.append(t.getFirstName()).append(" ").append(t.getLastName());
            if (i < teachers.size() - 1) sb.append(" | ");
        }

        sb.append(", Enrolled Students: ");
        if (enrolledStudents.isEmpty()) {
            sb.append("None");
        } else {
            for (int i = 0; i < enrolledStudents.size(); i++) {
                student s = enrolledStudents.get(i);
                sb.append(s.getFirstName()).append(" ").append(s.getLastName());
                if (i < enrolledStudents.size() - 1) sb.append(" | ");
            }
        }

        return sb.toString();
    }
}