package src.DataSaving;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SubjectInfo {
    private String courseId;
    private String courseName;
    private List<TeacherInfo> teachers;
    private List<StudentInfo> enrolledStudents;

    public SubjectInfo(String courseId, String courseName, TeacherInfo... teachers) {
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

    public List<TeacherInfo> getTeachers() {
        return teachers;
    }
    public List<StudentInfo> getEnrolledStudents() {
        return enrolledStudents;
    }
    public void enrollStudent(StudentInfo student) {
        enrolledStudents.add(student);
    }

   public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(courseId).append(",").append(courseName).append(", Teachers: ");
        for (int i = 0; i < teachers.size(); i++) {
            TeacherInfo t = teachers.get(i);
            sb.append(t.getFirstName()).append(" ").append(t.getLastName());
            if (i < teachers.size() - 1) sb.append(" | ");
        }

        sb.append(", Enrolled Students: ");
        if (enrolledStudents.isEmpty()) {
            sb.append("None");
        } else {
            for (int i = 0; i < enrolledStudents.size(); i++) {
                StudentInfo s = enrolledStudents.get(i);
                sb.append(s.getFirstName()).append(" ").append(s.getLastName());
                if (i < enrolledStudents.size() - 1) sb.append(" | ");
            }
        }

        return sb.toString();
    }
}



