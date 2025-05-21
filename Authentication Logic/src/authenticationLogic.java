import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthenticationLogic {

    // Hash the password using SHA-512
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 algorithm not found", e);
        }
    }

    // simulate retrieving stored hashed password from database
    public static String getStoredPasswordHashFromDB(String userID) {
        // will replace this logic with real DB retrieval
  
        if (userID.equals("12345")) { // Faculty
            return hashPassword("facultyPassword123");
        } else if (userID.equals("2025012345")) { // Student
            return hashPassword("studentPassword123");
        }
        return null; 
    }

   
    public static String determineRole(String userID) {
        if (!userID.matches("\\d+")) return "Invalid";
        int length = userID.length();
        if (length == 5) return "Faculty";
        if (length == 10) return "Student";
        return "Invalid";
    }

    // Authenticate user ID and password
    public static String authenticate(String enteredID, String enteredPassword) {
        String role = determineRole(enteredID);
        if (role.equals("Invalid")) return "Invalid";

        String storedHash = getStoredPasswordHashFromDB(enteredID);
        if (storedHash == null) return "Invalid";

        String enteredHash = hashPassword(enteredPassword);
        if (enteredHash.equals(storedHash)) {
            return role; // Login successful
        } else {
            return "Invalid"; // Password does not match
        }
    }

    // Called by the GUI(Andres) on login submission
    public static void handleLogin(String enteredID, String enteredPassword) {
        String result = authenticate(enteredID, enteredPassword);

        switch (result) {
            case "Faculty":
                System.out.println("Login successful");
                break;
            case "Student":
                System.out.println("Login successful");
                break;
            default:
                System.out.println("Invalid ID number or password. Please try again.");
                break;
        }
    }

    // Called during registration to hash a password
    public static String handleRegistrationPasswordHash(String plainPassword) {
        return hashPassword(plainPassword);
    }

    // main method for testing
    public static void main(String[] args) {
        System.out.println("Testing Login...");

        handleLogin("12345", "facultyPassword123");

        handleLogin("2025012345", "studentPassword123");

        handleLogin("2025012345", "wrongPassword");

        handleLogin("111", "password");
    }
}
