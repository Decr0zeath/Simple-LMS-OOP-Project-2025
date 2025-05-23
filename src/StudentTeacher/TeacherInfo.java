package StudentTeacher;

import User.User;

public class TeacherInfo extends User{
    private String course;
    public TeacherInfo(String firstName, String lastName, String accountID, String password, String course) {
        super(firstName, lastName, accountID, password, "Teacher");
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String toFileString() {
        return firstName + "," + lastName + "," + accountID + "," + password + "," + course;
    }
}
