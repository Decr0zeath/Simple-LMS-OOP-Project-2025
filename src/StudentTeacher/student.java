package StudentTeacher;

import User.User;

public class Student extends User {
    private String degree;
    private int year;

    public Student(String firstName, String lastName, String accountID, String password, String degree, int year) {
        super(firstName, lastName, accountID, password, "Student");  
        this.degree = degree;
        this.year = year;
    }

    public String getDegree() {
        return degree;
    }

    public int getYear() {
        return year;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toFileString() {
        return getFirstName() + "," + getLastName() + "," + getAccountID() + "," + getPassword() + "," + degree + "," + year + "," + role;
    }
}
