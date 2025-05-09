package classProject;

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
        public String toFileString() {
            return firstName + "," + lastName + "," + accountID + "," + password;
        }
}

