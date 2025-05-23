package StudentTeacher;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import StudentTeacher.student;
import StudentTeacher.Teacher;

public class Course {
    private final String courseId;
    private final String courseName;
    private List<Teacher> teachers;
    private List<Student> enrolledStudents;

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
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
    public void enrollStudent(Student student) {
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
                Student s = enrolledStudents.get(i);
                sb.append(s.getFirstName()).append(" ").append(s.getLastName());
                if (i < enrolledStudents.size() - 1) sb.append(" | ");
            }
        }

        return sb.toString();
    }
}