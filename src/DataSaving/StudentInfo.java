package src.DataSaving;

public class StudentInfo {
    private String firstName;
    private String lastName;
    private String accountID;
    private String password;
    private String degree;
    private int year;

    public StudentInfo(String firstName, String lastName, String accountID, String password, String degree, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountID = accountID;
        this.password = password;
        this.degree = degree;
        this.year = year;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getPassword() {
        return password;
    }

    public String getDegree() {
        return degree;
    }

    public int getYear() {
        return year;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toFileString() {
        return firstName + "," + lastName + "," + accountID + "," + password + "," + degree + "," + year;
    }
}

