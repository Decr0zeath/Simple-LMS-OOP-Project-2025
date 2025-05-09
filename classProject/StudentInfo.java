package classProject;

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
    
        public String toFileString() {
            return firstName + "," + lastName + "," + accountID + "," + password + "," + degree + "," + year;
        }
}
