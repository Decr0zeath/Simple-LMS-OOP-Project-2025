package src.DataSaving;

public class TeacherInfo {
    private String firstName;
    private String lastName;
    private String accountID;
    private String password;

    public TeacherInfo(String firstName, String lastName, String accountID, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountID = accountID;
        this.password = password;
    }

    // Getters
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

    // Setters
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

    public String toFileString() {
        return firstName + "," + lastName + "," + accountID + "," + password;
    }
}
