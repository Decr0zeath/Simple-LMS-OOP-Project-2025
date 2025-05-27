package StudentTeacher;

import User.User;

public class Teacher extends User{
    public Teacher(String firstName, String lastName, String accountID, String password) {
        super(firstName, lastName, accountID, password, "Teacher");

    }
    public String toFileString() {
        return firstName + "," + lastName + "," + accountID + "," + password + "," + role;
    }
}
